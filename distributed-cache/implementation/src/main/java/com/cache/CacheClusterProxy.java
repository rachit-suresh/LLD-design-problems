package com.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheClusterProxy<K, V> implements Repository<K, V> {
    private final Map<String, CacheNode<K, V>> nodeRegistry;
    private final DataDistributionStrategy<K> distributionStrategy;
    private final PrefetchStrategy<K> prefetchStrategy;
    private final RequestCollapser<K, V> collapser;
    private final Repository<K, V> dbClient;
    private final ExecutorService workerPool;

    public CacheClusterProxy(List<CacheNode<K, V>> nodes,
                             DataDistributionStrategy<K> distributionStrategy,
                             PrefetchStrategy<K> prefetchStrategy,
                             Repository<K, V> dbClient) {
        this.nodeRegistry = new HashMap<>();
        for (CacheNode<K, V> n : nodes) {
            this.nodeRegistry.put(n.getNodeId(), n);
        }
        this.distributionStrategy = distributionStrategy;
        this.prefetchStrategy = prefetchStrategy;
        this.collapser = new RequestCollapser<>();
        this.dbClient = dbClient;
        this.workerPool = Executors.newFixedThreadPool(4);
    }

    @Override
    public CompletableFuture<V> get(K key) {
        String targetNodeId = distributionStrategy.resolveNodeId(key);
        CacheNode<K, V> node = nodeRegistry.get(targetNodeId);

        V cachedValue = node.getValue(key);
        if (cachedValue != null) {
            System.out.println("[ClusterProxy] Cache HIT for key: " + key + " from Node: " + targetNodeId);
            return CompletableFuture.completedFuture(cachedValue);
        }

        System.out.println("[ClusterProxy] Cache MISS for key: " + key + ". Fetching from DB...");
        
        return collapser.executeOrWait(key, () -> dbClient.get(key)
            .thenApplyAsync(value -> {
                if (value != null) {
                    node.putKeyValue(key, value);
                    triggerAsyncPrefetch(key);
                }
                return value;
            }, workerPool));
    }

    @Override
    public void put(K key, V value) {
        // Write-through logic (or cache aside based on design)
        dbClient.put(key, value);
        String targetNodeId = distributionStrategy.resolveNodeId(key);
        CacheNode<K, V> node = nodeRegistry.get(targetNodeId);
        node.putKeyValue(key, value);
    }

    private void triggerAsyncPrefetch(K primaryKey) {
        List<K> targets = prefetchStrategy.computePrefetchTargets(primaryKey);
        for (K prefetchKey : targets) {
            workerPool.submit(() -> {
                // simple async warmup 
                get(prefetchKey); 
            });
        }
    }
}