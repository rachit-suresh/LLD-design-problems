package com.cache;

import java.util.LinkedHashSet;

public class LRUEvictionStrategy<K> implements EvictionStrategy<K> {
    // LinkedHashSet maintains insertion order, so we can mock an LRU queue
    private final LinkedHashSet<K> accessOrder;

    public LRUEvictionStrategy() {
        this.accessOrder = new LinkedHashSet<>();
    }

    @Override
    public synchronized void recordAccess(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public synchronized K evict() {
        if (accessOrder.isEmpty()) {
            return null;
        }
        K leastRecentlyUsed = accessOrder.iterator().next();
        accessOrder.remove(leastRecentlyUsed);
        return leastRecentlyUsed;
    }
}