package com.cache;

import java.util.Collections;
import java.util.List;

public class SimplePrefetchStrategy<K> implements PrefetchStrategy<K> {
    @Override
    public List<K> computePrefetchTargets(K key) {
        // Simulated: do not prefetch any by default to keep it simple,
        // or could implement logic like if key is "user:1", prefetch "user:2"
        return Collections.emptyList();
    }
}