package org.example;

import java.net.http.HttpResponse;

public class RateLimiterProxy implements IRemoteResource {
    private final IRateLimitingStrategy strategy;
    private final IRemoteResource remoteResource;

    public RateLimiterProxy(IRateLimitingStrategy strategy, IRemoteResource remoteResource) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy must not be null");
        }
        if (remoteResource == null) {
            throw new IllegalArgumentException("remoteResource must not be null");
        }
        this.strategy = strategy;
        this.remoteResource = remoteResource;
    }

    @Override
    public HttpResponse<String> sendRequest(RequestEntity requestEntity) {
        if (requestEntity == null) {
            throw new IllegalArgumentException("requestEntity must not be null");
        }

        if (strategy.isAllowed(requestEntity.getClientId())) {
            return remoteResource.sendRequest(requestEntity);
        }

        return SimpleHttpResponse.of(
                429,
                "Rate limit exceeded for client: " + requestEntity.getClientId()
        );
    }
}
