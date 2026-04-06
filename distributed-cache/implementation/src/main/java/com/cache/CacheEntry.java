package com.cache;

public class CacheEntry<V> {
    private final V value;
    private final long expiryTimestampMillis;

    public CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.expiryTimestampMillis = System.currentTimeMillis() + ttlMillis;
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTimestampMillis;
    }
}