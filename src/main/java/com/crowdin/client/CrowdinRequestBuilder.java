package com.crowdin.client;


import com.crowdin.common.models.Pageable;
import com.crowdin.exception.CrowdinException;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings({"WeakerAccess", "unchecked"})
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
    private Pageable pageable;
    private HttpClient.HttpMethod method;
    private String apiKey;

    public static <R> CrowdinRequestBuilder<R> builder(String baseUrl, TypeReference<R> responseType) {
        CrowdinRequestBuilder<R> builder = new CrowdinRequestBuilder<>();
        builder.baseUrl = baseUrl;
        builder.responseType = responseType;
        return builder;
    }

    public static <K> K execute(CrowdinRequestBuilder builder) {
        return (K) builder.execute();

    }

    public CrowdinRequestBuilder<R> setResponseType(TypeReference<R> responseType) {
        this.responseType = responseType;
        return this;
    }

    public CrowdinRequestBuilder<R> pageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public CrowdinRequestBuilder<R> method(HttpClient.HttpMethod method) {
        this.method = method;
        return this;
    }

    public CrowdinRequestBuilder<R> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
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

    public R execute() {
        preparationRequest();
        return HttpClient.doRequest(url, requestBody, responseType, method);
    }

    private void preparationRequest() {
        concatUriWithPath();
        validatePathParameters();
        fillUrl();
        addPaginationToRequest();
    }

    private void concatUriWithPath() {
        url = !baseUrl.endsWith(URI_DELIMITER) && !path.startsWith(URI_DELIMITER)
                ? baseUrl.concat(URI_DELIMITER).concat(path)
                : baseUrl.concat(path);
    }

    private void addPaginationToRequest() {
        if (pageable == null) {
            return;
        }

        if (!url.contains(REQUEST_PARAMETERS_START_PREFIX)) {
            url = url.concat(REQUEST_PARAMETERS_START_PREFIX);
        }

        url = url.concat("offset=").concat(pageable.getOffset().toString());
        url = url.concat(REQUEST_PARAMETERS_DELIMITER).concat("limit=").concat(pageable.getLimit().toString());

        url = url.concat(REQUEST_PARAMETERS_DELIMITER).concat("account-key=").concat(apiKey);
    }

    private void fillUrl() {
        url = String.format(url, pathParams);
    }

    private void validatePathParameters() {
        Optional<Object> first = Stream.of(pathParams).filter(s -> s == null || s.toString().isEmpty()).findFirst();
        if (first.isPresent()) throw new CrowdinException("Some of path parameter is null or empty");
    }

    public CrowdinRequestBuilder<R> apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}