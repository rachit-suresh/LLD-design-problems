package com.cache;

public interface EvictionStrategy<K> {
    void recordAccess(K key);
    K evict();
}