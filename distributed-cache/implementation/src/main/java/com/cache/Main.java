package com.cache;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static <K, V> void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Initializing Distributed Cache Cluster...");

        // Setup Nodes with LRU Eviction
        CacheNode<String, String> nodeA = new CacheNode<>("Node-A", new LRUEvictionStrategy<>(), 2);
        CacheNode<String, String> nodeB = new CacheNode<>("Node-B", new LRUEvictionStrategy<>(), 2);
        CacheNode<String, String> nodeC = new CacheNode<>("Node-C", new LRUEvictionStrategy<>(), 2);

        List<CacheNode<String, String>> clusterNodes = Arrays.asList(nodeA, nodeB, nodeC);
        
        // Distribution via Simple Modulus Hash
        DataDistributionStrategy<String> distributionStrategy = new SimpleModulusDistributionStrategy<>(
                Arrays.asList(nodeA.getNodeId(), nodeB.getNodeId(), nodeC.getNodeId())
        );

        // Wiring standard infra
        PrefetchStrategy<String> prefetchStrategy = new SimplePrefetchStrategy<>();
        Repository<String, String> dbClient = new DatabaseClient<>();

        CacheClusterProxy<String, String> proxy = new CacheClusterProxy<>(
                clusterNodes, distributionStrategy, prefetchStrategy, dbClient
        );

        // Simulating usage
        System.out.println("\n--- Writing Data to Cache Cluster ---");
        proxy.put("user1", "Alice");
        proxy.put("user2", "Bob");
        proxy.put("user3", "Charlie");
        proxy.put("user4", "Dave"); // Will trigger LRU logic down the road inside nodes based on distribution modulo

        System.out.println("\n--- Reading Data from Cache Cluster ---");
        System.out.println("Result user1: " + proxy.get("user1").get()); // Wait for real value
        System.out.println("Result user1 (again): " + proxy.get("user1").get());

        System.out.println("Result user3: " + proxy.get("user3").get());
        
        // Force LRU Eviction if they map to the same node
        proxy.put("user10", "Eve");
        proxy.put("user11", "Frank");
        proxy.put("user12", "Grace");
        proxy.put("user13", "Helen");
        proxy.put("user14", "Ivan");
        
        System.out.println("Simulation completed.");
        System.exit(0);
    }
}