package crowdin.api.client.core.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HttpConfig {

    private Map<String, ? extends Optional> urlParams = new HashMap<>();
    private Map<String, ?> headers = new HashMap<>();

    public HttpConfig(Map<String, Optional<?>> urlParams, Map<String, ?> headers) {
        this.urlParams = urlParams;
        this.headers = headers;
    }

    public HttpConfig(Map<String, ? extends Optional> urlParams) {
        this.urlParams = urlParams;
    }

    public HttpConfig() {
    }

    public Map<String, ? extends Optional> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, Optional<?>> urlParams) {
        this.urlParams = urlParams;
    }

    public Map<String, ?> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, ?> headers) {
        this.headers = headers;
    }
}
