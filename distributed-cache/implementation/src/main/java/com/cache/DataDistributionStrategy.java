package com.cache;

public interface DataDistributionStrategy<K> {
    String resolveNodeId(K key);
}