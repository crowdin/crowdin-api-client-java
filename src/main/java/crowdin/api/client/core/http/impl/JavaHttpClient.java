package crowdin.api.client.core.http.impl;

import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.HttpConfig;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.http.exceptions.HttpBadRequestException;
import crowdin.api.client.core.http.exceptions.HttpException;
import crowdin.api.client.core.model.Credentials;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaHttpClient implements HttpClient {

    private final java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
    private final Credentials credentials;
    private final JsonTransformer jsonTransformer;

    public JavaHttpClient(Credentials credentials, JsonTransformer jsonTransformer) {
        this.credentials = credentials;
        this.jsonTransformer = jsonTransformer;
    }

    @Override
    public <T> T get(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpRequest.Builder::GET);
    }

    @Override
    public <T> T delete(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpRequest.Builder::DELETE);
    }

    @Override
    public <T> T head(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, b -> b.method("HEAD", HttpRequest.BodyPublishers.noBody()));
    }

    @Override
    public <T, V> T post(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, b -> b.POST(this.buildBody(data)));
    }

    @Override
    public <T, V> T put(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, b -> b.PUT(this.buildBody(data)));
    }

    @Override
    public <T, V> T patch(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, b -> b.method("PATCH", this.buildBody(data)));
    }

    private <T, V> T request(String url,
                             HttpConfig config,
                             Class<T> clazz,
                             Function<HttpRequest.Builder, HttpRequest.Builder> applyMethod) throws HttpException, HttpBadRequestException {
        var request = applyMethod.apply(prepareRequest(url, config)).build();
        try {
            HttpResponse<String> resp = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() < 200 || resp.statusCode() >= 300) {
                RuntimeException exception;
                try {
                    exception = this.jsonTransformer.parse(resp.body(), HttpException.class);
                } catch (Exception e) {
                    exception = this.jsonTransformer.parse(resp.body(), HttpBadRequestException.class);
                }
                throw exception;
            }
            //plain response
            if (String.class.equals(clazz)) {
                return (T) resp.body();
            }
            //not interested in response at all
            if (Void.class.equals(clazz)) {
                return null;
            }
            return this.jsonTransformer.parse(resp.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw HttpException.fromMessage(e.getMessage());
        }
    }

    private HttpRequest.Builder prepareRequest(String url, HttpConfig config) {
        var builder = HttpRequest.newBuilder()
                .uri(URI.create(this.appendUrlParams(url, config.getUrlParams())))
                .header("Authorization", "Bearer " + this.credentials.getToken());
        for (Map.Entry<String, ?> entry : config.getHeaders().entrySet()) {
            builder = builder.header(entry.getKey(), entry.getValue().toString());
        }
        return builder;
    }

    private String appendUrlParams(String url, Map<String, ? extends Optional> urlParams) {
        return url + urlParams.entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .map(entry -> entry.getKey() + "=" + entry.getValue().get())
                .collect(Collectors.joining("&", "?", ""));
    }

    private <V> HttpRequest.BodyPublisher buildBody(V data) {
        if (Objects.isNull(data)) {
            return HttpRequest.BodyPublishers.noBody();
        }
        if (data instanceof String) {
            return HttpRequest.BodyPublishers.ofString((String) data);
        }
        return HttpRequest.BodyPublishers.ofString(this.jsonTransformer.convert(data));
    }
}
