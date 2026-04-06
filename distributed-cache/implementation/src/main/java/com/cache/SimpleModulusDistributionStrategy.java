package com.cache;

import java.util.List;

public class SimpleModulusDistributionStrategy<K> implements DataDistributionStrategy<K> {
    private final List<String> nodeIds;

    public SimpleModulusDistributionStrategy(List<String> nodeIds) {
        if (nodeIds == null || nodeIds.isEmpty()) {
            throw new IllegalArgumentException("Node list cannot be empty");
        }
        this.nodeIds = nodeIds;
    }

    @Override
    public String resolveNodeId(K key) {
        int hash = key.hashCode();
        // Handle negative hashes
        int index = Math.abs(hash) % nodeIds.size();
        return nodeIds.get(index);
    }
}