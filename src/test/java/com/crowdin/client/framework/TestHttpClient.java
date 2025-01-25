package com.crowdin.client.framework;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestHttpClient implements HttpClient {

    private Map<String, List<RequestMock>> requestMocks = new HashMap<>();
    private JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();
    private ObjectMapper objectMapper = jsonTransformer.getObjectMapper();

    public void initializeMocks(List<RequestMock> requestMocks) {
        this.requestMocks = requestMocks.stream().collect(
            Collectors.groupingBy(
                mock -> getRequestKey(mock.getUrl(), mock.getHttpMethod())
            )
        );
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
        try {
            String requestKey = this.getRequestKey(url, method);
            if (!this.requestMocks.containsKey(requestKey)) {
                throw new AssertionError("No match for request : " + requestKey);
            }

            List<RequestMock> requestMocks = this.requestMocks.get(requestKey);
            for (int i = 0; i < requestMocks.size(); ++i) {
                RequestMock requestMock = requestMocks.get(i);
                if (!config.getHeaders().equals(requestMock.getHeaders())) {
                    throw new AssertionError("No match for request headers : " + requestKey);
                }
                Map<String, Object> urlParams = config.getUrlParams().entrySet().stream()
                    .filter(entry -> entry.getValue().isPresent())
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
                if (!urlParams.equals(requestMock.getUrlParams())) {
                    throw new AssertionError("No match for url query parameters : " + requestKey);
                }

                //request
                if (data != null) {
                    if (requestMock.getRequestFile() == null || requestMock.getRequestFile().isEmpty()) {
                        throw new AssertionError("No request body mock provided : " + requestKey);
                    }
                    InputStream requestInputStream = this.getClass().getClassLoader().getResourceAsStream(requestMock.getRequestFile());
                    if (requestInputStream == null) {
                        throw new AssertionError("Failed to load request mock payload " + requestMock.getRequestFile() + " for request" + requestKey);
                    }
                    String requestPayloadMock = new BufferedReader(new InputStreamReader(requestInputStream)).lines().collect(Collectors.joining("\n"));
                    boolean sameRequests;
                    if (data instanceof String) {
                        sameRequests = data.equals(requestPayloadMock);
                    } else {
                        String jsonData = this.jsonTransformer.convert(data);
                        sameRequests = this.objectMapper.readTree(requestPayloadMock).equals(this.objectMapper.readTree(jsonData));
                    }
                    if (!sameRequests && i == requestMocks.size() - 1) {
                        throw new AssertionError("No match for request payload " + requestKey);
                    } else if (!sameRequests && i < requestMocks.size() - 1) {
                        continue;
                    }
                }

                //response
                //not interested in response at all
                if (Void.class.equals(clazz)) {
                    return null;
                }
                if (requestMock.getResponseFile() == null || requestMock.getResponseFile().isEmpty()) {
                    throw new AssertionError("No response body mock provided : " + requestKey);
                }
                InputStream responseInputStream = this.getClass().getClassLoader().getResourceAsStream(requestMock.getResponseFile());
                if (responseInputStream == null) {
                    throw new AssertionError("Failed to load response mock payload " + requestMock.getResponseFile() + " for request" + requestKey);
                }
                String responsePayloadMock = new BufferedReader(new InputStreamReader(responseInputStream)).lines().collect(Collectors.joining("\n"));
                //plain response
                if (String.class.equals(clazz)) {
                    return (T) responsePayloadMock;
                }
                return this.jsonTransformer.parse(responsePayloadMock, clazz);
            }
        } catch (Exception e) {
            throw new AssertionError("Failed to execute test", e);
        }
        return null;
    }

    private String getRequestKey(String url, String httpMethod) {
        return httpMethod + " | " + url;
    }
}
