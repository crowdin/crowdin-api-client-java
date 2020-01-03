package com.crowdin.client;


import com.crowdin.common.models.Pageable;
import com.crowdin.exception.CrowdinException;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"WeakerAccess", "unchecked", "UnusedReturnValue"})
public class CrowdinRequestBuilder<R> {

    public static final String URI_DELIMITER = "/";
    public static final String REQUEST_PARAMETERS_START_PREFIX = "?";
    public static final String REQUEST_PARAMETERS_DELIMITER = "&";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String UPLOAD_STORAGE_HEADER = "Crowdin-API-FileName";

    private String url;

    private String path;
    private String baseUrl;
    private TypeReference<R> responseType;
    private Object requestBody;
    private Object[] pathParams;
    private CrowdinHttpClient.HttpMethod method;
    private Map<String, String> requestParams;
    private MultivaluedMap<String, Object> headers;

    public static <R> CrowdinRequestBuilder<R> builder(String baseUrl, TypeReference<R> responseType) {
        CrowdinRequestBuilder<R> builder = new CrowdinRequestBuilder<>();
        builder.baseUrl = baseUrl;
        builder.responseType = responseType;
        builder.requestParams = new HashMap<>();
        builder.headers = new MultivaluedHashMap<>();

        return builder;
    }

    public static <K> K execute(CrowdinRequestBuilder builder) {
        return (K) builder.getResponseEntity();
    }

    public CrowdinRequestBuilder<R> pageable(Pageable pageable) {
        if (pageable != null) {
            this.requestParams.put("offset", pageable.getOffset().toString());
            this.requestParams.put("limit", pageable.getLimit().toString());
        }
        return this;
    }

    public CrowdinRequestBuilder<R> method(CrowdinHttpClient.HttpMethod method) {
        this.method = method;
        return this;
    }

    public CrowdinRequestBuilder<R> path(String path) {
        this.path = path;
        return this;
    }

    public <I> CrowdinRequestBuilder<R> requestBody(I requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CrowdinRequestBuilder<R> pathParams(String... pathParams) {
        this.pathParams = pathParams;
        return this;
    }

    public R getResponseEntity() {
        if (responseType == null) {
            throw new CrowdinException("Not support this method without type of response entity. Specify response type.");
        }

        prepareRequest();
        return CrowdinHttpClient.executeRequest(buildRequest());
    }

    public Response execute() {
        prepareRequest();
        return CrowdinHttpClient.doRequestAndValidateResponse(buildRequest());
    }

    private CrowdinHttpClient.RequestWrapper<R> buildRequest() {
        CrowdinHttpClient.RequestWrapper<R> requestWrapper = new CrowdinHttpClient.RequestWrapper<>(url, requestBody, responseType, method);
        requestWrapper.setHeaders(this.headers);
        return requestWrapper;
    }


    public CrowdinRequestBuilder<R> header(String key, Object value) {
        this.headers.add(key, value);
        return this;
    }

    private void prepareRequest() {
        concatUriWithPath();
        validatePathParameters();
        fillUrl();
        addParamsToUrl();
    }

    private void addParamsToUrl() {
        if (!url.contains(REQUEST_PARAMETERS_START_PREFIX)) {
            url = url.concat(REQUEST_PARAMETERS_START_PREFIX);
        }

        url = url.concat(
                requestParams
                        .entrySet()
                        .stream()
                        .map(this::getParamStringFromNameAndValueEntry)
                        .collect(Collectors.joining(REQUEST_PARAMETERS_DELIMITER)));
    }

    private String getParamStringFromNameAndValueEntry(Map.Entry<String, String> paramNameToValue) {
        return paramNameToValue.getKey() + "=" + paramNameToValue.getValue();
    }

    private void concatUriWithPath() {
        url = !baseUrl.endsWith(URI_DELIMITER) && !path.startsWith(URI_DELIMITER)
                ? baseUrl.concat(URI_DELIMITER).concat(path)
                : baseUrl.concat(path);
    }

    private void fillUrl() {
        url = String.format(url, pathParams);
    }

    private void validatePathParameters() {
        Optional<Object> first = Stream.of(pathParams).filter(s -> s == null || s.toString().isEmpty()).findFirst();
        if (first.isPresent()) throw new CrowdinException("Some of path parameter is null or empty");
    }

    public CrowdinRequestBuilder<R> token(String token) {
        this.header("Authorization", TOKEN_PREFIX + token);
        return this;
    }

    public CrowdinRequestBuilder<R> login(String login) {
        this.requestParams.put("login", login);
        return this;
    }
}