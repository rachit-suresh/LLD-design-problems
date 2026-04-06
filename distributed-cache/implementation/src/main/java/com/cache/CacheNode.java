package com.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CacheNode<K, V> {
    private final String nodeId;
    private final ConcurrentHashMap<K, CacheEntry<V>> store;
    private final EvictionStrategy<K> evictionStrategy;
    private final int maxCapacity;
    private final long defaultTtlMillis = 60000; // 60 seconds

    public CacheNode(String nodeId, EvictionStrategy<K> evictionStrategy, int maxCapacity) {
        this.nodeId = nodeId;
        this.store = new ConcurrentHashMap<>();
        this.evictionStrategy = evictionStrategy;
        this.maxCapacity = maxCapacity;
    }

    public V getValue(K key) {
        sweepExpiredKeys(); // cleanup optionally on read
        CacheEntry<V> entry = store.get(key);
        if (entry != null) {
            if (!entry.isExpired()) {
                evictionStrategy.recordAccess(key);
                return entry.getValue();
            } else {
                store.remove(key); // Evict expired
            }
        }
        return null;
    }

    public void putKeyValue(K key, V value) {
        sweepExpiredKeys(); // periodic sweeps logic mock
        enforceCapacityBounds();
        
        store.put(key, new CacheEntry<>(value, defaultTtlMillis));
        evictionStrategy.recordAccess(key);
        System.out.println("[Node: " + nodeId + "] Cached: " + key);
    }

    private void enforceCapacityBounds() {
        if (store.size() >= maxCapacity) {
            K victim = evictionStrategy.evict();
            if (victim != null) {
                store.remove(victim);
                System.out.println("[Node: " + nodeId + "] Evicted LRU Key: " + victim);
            }
        }
    }

    private void sweepExpiredKeys() {
        // Basic sweep for expired entries
        store.forEach((k, v) -> {
            if (v.isExpired()) {
                store.remove(k);
            }
        });
    }

    public String getNodeId() {
        return nodeId;
    }
}