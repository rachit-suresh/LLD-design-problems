import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RateLimiterProxy implements IRemoteResource{
    IRateLimitingStrategy strategy;
    IRemoteResource remoteResource;

    @Override
    public  sendRequest(RequestEntity requestEntity) {
        if(strategy.isAllowed()) return remoteResource.sendRequest(requestEntity);
        return HttpResponse.
    }
}
