package crowdin.api.client.core.http.impl;

import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.HttpConfig;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.http.exceptions.HttpBadRequestException;
import crowdin.api.client.core.http.exceptions.HttpException;
import crowdin.api.client.core.model.Credentials;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApacheHttpClient implements HttpClient {

    private final Credentials credentials;
    private final JsonTransformer jsonTransformer;

    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    @Override
    public <T> T get(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpGet.METHOD_NAME);
    }

    @Override
    public <T> T delete(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpDelete.METHOD_NAME);
    }

    @Override
    public <T> T head(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpHead.METHOD_NAME);
    }

    @Override
    public <T, V> T post(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpPost.METHOD_NAME);
    }

    @Override
    public <T, V> T put(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpPut.METHOD_NAME);
    }

    @Override
    public <T, V> T patch(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, config, clazz, HttpPatch.METHOD_NAME);
    }

    private <T, V> T request(String url,
                             HttpConfig config,
                             Class<T> clazz,
                             String method) throws HttpException, HttpBadRequestException {
        HttpUriRequest request = this.buildRequest(method, url, config);
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode >= 300) {
                String error = this.toString(response.getEntity());
                RuntimeException exception;
                try {
                    exception = this.jsonTransformer.parse(error, HttpException.class);
                } catch (Exception e) {
                    exception = this.jsonTransformer.parse(error, HttpBadRequestException.class);
                }
                throw exception;
            }
            //plain response
            if (String.class.equals(clazz)) {
                return (T) this.toString(response.getEntity());
            }
            //not interested in response at all
            if (Void.class.equals(clazz)) {
                return null;
            }
            return this.jsonTransformer.parse(this.toString(response.getEntity()), clazz);
        } catch (Exception e) {
            throw HttpException.fromMessage(e.getMessage());
        }
    }

    private HttpUriRequest buildRequest(String httpMethod, String url, HttpConfig config) {
        RequestBuilder requestBuilder = RequestBuilder.create(httpMethod);
        requestBuilder.setUri(URI.create(this.appendUrlParams(url, config.getUrlParams())));
        requestBuilder.addHeader("Authorization", "Bearer " + this.credentials.getToken());
        for (Map.Entry<String, ?> entry : config.getHeaders().entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
        }
        return requestBuilder.build();
    }

    private String appendUrlParams(String url, Map<String, ? extends Optional> urlParams) {
        return url + urlParams.entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .map(entry -> entry.getKey() + "=" + entry.getValue().get())
                .collect(Collectors.joining("&", "?", ""));
    }

    private String toString(HttpEntity entity) throws IOException {
        final InputStream stream = entity.getContent();
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
        int charsRead;
        while ((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }
}
