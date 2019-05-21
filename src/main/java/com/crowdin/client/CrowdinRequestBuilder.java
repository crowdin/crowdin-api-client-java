package com.crowdin.client;


import com.crowdin.common.models.Pageable;
import com.crowdin.exception.CrowdinException;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

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

    private String url;

    private String path;
    private String baseUrl;
    private TypeReference<R> responseType;
    private Object requestBody;
    private Object[] pathParams;
    private CrowdinHttpClient.HttpMethod method;
    private String apiKey;
    private Map<String, String> requestParams;

    public static <R> CrowdinRequestBuilder<R> builder(String baseUrl, TypeReference<R> responseType) {
        CrowdinRequestBuilder<R> builder = new CrowdinRequestBuilder<>();
        builder.baseUrl = baseUrl;
        builder.responseType = responseType;
        builder.requestParams = new HashMap<>();

        // todo remove for test
//        builder.requestParams.put("login", "nutelka");
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
        return CrowdinHttpClient.executeRequest(url, requestBody, responseType, method);
    }

    public Response execute() {
        prepareRequest();
        return CrowdinHttpClient.doRequestAndValidateResponse(url, requestBody, method);
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

    public CrowdinRequestBuilder<R> apiKey(String apiKey) {
        this.requestParams.put("account-key", apiKey);
        return this;
    }
}