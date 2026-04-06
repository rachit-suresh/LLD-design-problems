package org.example;

public interface IRateLimitingStrategy {
    boolean isAllowed(String clientId);
}
