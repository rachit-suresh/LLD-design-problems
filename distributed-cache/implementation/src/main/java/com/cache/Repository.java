package com.cache;

import java.util.concurrent.CompletableFuture;

public interface Repository<K, V> {
    CompletableFuture<V> get(K key);
    void put(K key, V value);
}