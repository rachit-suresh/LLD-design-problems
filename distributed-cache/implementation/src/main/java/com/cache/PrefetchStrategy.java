package com.cache;

import java.util.List;

public interface PrefetchStrategy<K> {
    List<K> computePrefetchTargets(K key);
}