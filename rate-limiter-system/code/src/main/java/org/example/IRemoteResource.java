package org.example;

import java.net.http.HttpResponse;

public interface IRemoteResource {
    HttpResponse<String> sendRequest(RequestEntity requestEntity);
}
