package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements IRateLimitingStrategy {
    private final int maxRequests;
    private final long windowInMillis;
    private final Map<String, Deque<Long>> requestLogByClient;

    public SlidingWindowRateLimiter(int maxRequests, long windowInMillis) {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests must be greater than 0");
        }
        if (windowInMillis <= 0) {
            throw new IllegalArgumentException("windowInMillis must be greater than 0");
        }
        this.maxRequests = maxRequests;
        this.windowInMillis = windowInMillis;
        this.requestLogByClient = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isAllowed(String clientId) {
        long now = System.currentTimeMillis();
        Deque<Long> requestTimes = requestLogByClient.computeIfAbsent(clientId, key -> new ArrayDeque<>());

        synchronized (requestTimes) {
            long oldestAllowedTimestamp = now - windowInMillis;
            while (!requestTimes.isEmpty() && requestTimes.peekFirst() <= oldestAllowedTimestamp) {
                requestTimes.pollFirst();
            }

            if (requestTimes.size() >= maxRequests) {
                return false;
            }

            requestTimes.offerLast(now);
            return true;
        }
    }
}
