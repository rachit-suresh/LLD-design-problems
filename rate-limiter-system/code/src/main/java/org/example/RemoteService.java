package org.example;

import java.net.http.HttpResponse;

public class RemoteService implements IRemoteResource {
    @Override
    public HttpResponse<String> sendRequest(RequestEntity requestEntity) {
        String body = "Processed request for client=" + requestEntity.getClientId()
                + ", endpoint=" + requestEntity.getEndpoint()
                + ", payload=" + requestEntity.getPayload();
        return SimpleHttpResponse.of(200, body);
    }
}
