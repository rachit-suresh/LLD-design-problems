import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IRemoteResource {
    HttpResponse sendRequest(RequestEntity requestEntity);
}
