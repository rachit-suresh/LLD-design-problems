package com.cache;

import java.util.concurrent.CompletableFuture;

public class DatabaseClient<K, V> implements Repository<K, V> {
    @Override
    public CompletableFuture<V> get(K key) {
        // Simulated slow DB call
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null; // Should return simulated value based on key in real usage
        });
    }

    @Override
    public void put(K key, V value) {
        // Simulated DB write
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Saved " + key + " = " + value + " to Database.");
    }
}