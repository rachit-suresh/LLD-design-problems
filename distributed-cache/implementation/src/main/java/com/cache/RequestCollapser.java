package com.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RequestCollapser<K, V> {
    private final ConcurrentHashMap<K, CompletableFuture<V>> inflightRequests = new ConcurrentHashMap<>();

    public CompletableFuture<V> executeOrWait(K key, Callable<CompletableFuture<V>> dbCall) {
        return inflightRequests.computeIfAbsent(key, k -> {
            try {
                return (CompletableFuture<V>) dbCall.call()
                        .whenComplete((result, ex) -> inflightRequests.remove(k)); // Remove when complete
            } catch (Exception e) {
                inflightRequests.remove(k);
                return CompletableFuture.failedFuture(e);
            }
        });
    }
}