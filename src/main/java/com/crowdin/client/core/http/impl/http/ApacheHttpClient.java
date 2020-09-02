package com.crowdin.client.core.http.impl.http;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApacheHttpClient implements HttpClient {

    private final Credentials credentials;
    private final JsonTransformer jsonTransformer;
    private final Map<String, ?> defaultHeaders;
    private ClientConfig.Host proxy;
    private ClientConfig.UsernamePasswordCredentials proxyCreds;

    private final CloseableHttpClient httpClient;

    public ApacheHttpClient(Credentials credentials, JsonTransformer jsonTransformer, Map<String, ?> defaultHeaders) {
        this.credentials = credentials;
        this.jsonTransformer = jsonTransformer;
        this.defaultHeaders = defaultHeaders;
        this.httpClient = HttpClientBuilder.create().build();
    }

    public ApacheHttpClient(Credentials credentials, JsonTransformer jsonTransformer, Map<String, ?> defaultHeaders, ClientConfig.Host proxy, ClientConfig.UsernamePasswordCredentials proxyCreds) {
        this.credentials = credentials;
        this.jsonTransformer = jsonTransformer;
        this.defaultHeaders = defaultHeaders;
        this.proxy = proxy;
        this.proxyCreds = proxyCreds;
        this.httpClient = (proxy != null)
            ? HttpClientBuilder.create()
                .setProxy(new HttpHost(proxy.getHost(), proxy.getPort()))
                .setDefaultCredentialsProvider((proxyCreds != null)
                    ? new BasicCredentialsProvider() {{
                            setCredentials(new AuthScope(proxy.getHost(), proxy.getPort()), new UsernamePasswordCredentials(proxyCreds.getUsername(), proxyCreds.getPassword()));
                        }}
                    : new BasicCredentialsProvider())
                .build()
            : HttpClientBuilder.create().build();
    }

    @Override
    public <T> T get(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpGet.METHOD_NAME);
    }

    @Override
    public <T> T delete(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpDelete.METHOD_NAME);
    }

    @Override
    public <T> T head(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpHead.METHOD_NAME);
    }

    @Override
    public <T, V> T post(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPost.METHOD_NAME);
    }

    @Override
    public <T, V> T put(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPut.METHOD_NAME);
    }

    @Override
    public <T, V> T patch(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPatch.METHOD_NAME);
    }

    private <T, V> T request(String url,
                             V data,
                             HttpRequestConfig config,
                             Class<T> clazz,
                             String method) throws HttpException, HttpBadRequestException {
        HttpUriRequest request = this.buildRequest(method, url, data, config);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
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
        } catch (IOException e) {
            throw HttpException.fromMessage(e.getMessage());
        }
    }

    private <V> HttpUriRequest buildRequest(String httpMethod, String url, V data, HttpRequestConfig config) {
        RequestBuilder requestBuilder = RequestBuilder.create(httpMethod);
        requestBuilder.setUri(URI.create(this.appendUrlParams(url, config.getUrlParams())));
        requestBuilder.addHeader("Authorization", "Bearer " + this.credentials.getToken());
        if (data != null) {
            HttpEntity entity;
            if (data instanceof InputStream) {
                entity = new InputStreamEntity((InputStream) data);
            } else if (data instanceof String) {
                entity = new StringEntity((String) data, ContentType.APPLICATION_OCTET_STREAM);
            } else {
                entity = new StringEntity(this.jsonTransformer.convert(data), ContentType.APPLICATION_JSON);
            }
            requestBuilder.setEntity(entity);
        }
        Map<String, Object> headers = new HashMap<>();
        headers.putAll(config.getHeaders());
        headers.putAll(this.defaultHeaders);
        for (Map.Entry<String, ?> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
        }
        return requestBuilder.build();
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
