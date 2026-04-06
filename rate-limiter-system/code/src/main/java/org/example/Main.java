package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IRateLimitingStrategy strategy = new SlidingWindowRateLimiter(3, 5_000);
        IRemoteResource remoteService = new RemoteService();
        IRemoteResource proxy = new RateLimiterProxy(strategy, remoteService);

        String clientId = "client-123";
        for (int i = 1; i <= 5; i++) {
            RequestEntity request = new RequestEntity(clientId, "/orders", "request-" + i);
            var response = proxy.sendRequest(request);

            System.out.println(
                    "Attempt " + i
                            + " -> status=" + response.statusCode()
                            + ", body=" + response.body()
            );
            Thread.sleep(700);
        }

        System.out.println("Waiting for window to reset...");
        Thread.sleep(5_000);

        var resetResponse = proxy.sendRequest(new RequestEntity(clientId, "/orders", "after-window"));
        System.out.println(
                "After window reset -> status=" + resetResponse.statusCode()
                        + ", body=" + resetResponse.body()
        );
    }
}