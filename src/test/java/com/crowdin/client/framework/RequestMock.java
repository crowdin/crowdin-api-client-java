package com.crowdin.client.framework;

import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.Map;

@Data
public class RequestMock {
    @NonNull
    private final String url;
    private final String requestFile;
    private final String responseFile;
    @NonNull
    private final String httpMethod;
    @NonNull
    private final Map<String, ?> urlParams;
    @NonNull
    private final Map<String, ?> headers;

    public static RequestMock build(String url, String httpMethod, String requestFile, String responseFile) {
        return new RequestMock(
                url,
                requestFile,
                responseFile,
                httpMethod,
                Collections.emptyMap(),
                Collections.emptyMap()
        );
    }

    public static RequestMock build(String url, String httpMethod, String responseFile, Map<String, ?> urlParams) {
        return new RequestMock(
                url,
                null,
                responseFile,
                httpMethod,
                urlParams,
                Collections.emptyMap()
        );
    }

    public static RequestMock build(String url, String httpMethod, String responseFile) {
        return new RequestMock(
                url,
                null,
                responseFile,
                httpMethod,
                Collections.emptyMap(),
                Collections.emptyMap()
        );
    }

    public static RequestMock build(String url, String httpMethod) {
        return new RequestMock(
                url,
                null,
                null,
                httpMethod,
                Collections.emptyMap(),
                Collections.emptyMap()
        );
    }

    public static RequestMock buildWithRequestFileOnly(String url, String httpMethod, String requestFile) {
        return new RequestMock(
                url,
                requestFile,
                null,
                httpMethod,
                Collections.emptyMap(),
                Collections.emptyMap()
        );
    }
}
