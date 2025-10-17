package com.crowdin.client.core.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Configuration for http request (query parameters, headers)
 */
public class HttpRequestConfig {

    private Map<String, ? extends Optional> urlParams = new HashMap<>();
    private Map<String, ?> headers = new HashMap<>();

    public HttpRequestConfig(Map<String, Optional<?>> urlParams, Map<String, ?> headers) {
        this.urlParams = urlParams;
        this.headers = headers;
    }

    public HttpRequestConfig(Map<String, ? extends Optional> urlParams) {
        this.urlParams = urlParams;
    }

    public HttpRequestConfig() {
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

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1) {
        return new HashMap<String, T>() {{
            put(k1, v1);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9, String k10, T v10) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
            put(k10, v10);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9, String k10, T v10, String k11, T v11) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
            put(k10, v10);
            put(k11, v11);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9, String k10, T v10, String k11, T v11, String k12, T v12) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
            put(k10, v10);
            put(k11, v11);
            put(k12, v12);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9, String k10, T v10, String k11, T v11, String k12, T v12, String k13, T v13) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
            put(k10, v10);
            put(k11, v11);
            put(k12, v12);
            put(k13, v13);
        }};
    }

    public static <T extends Optional> Map<String, T> buildUrlParams(String k1, T v1, String k2, T v2, String k3, T v3, String k4, T v4, String k5, T v5, String k6, T v6, String k7, T v7, String k8, T v8, String k9, T v9, String k10, T v10, String k11, T v11, String k12, T v12, String k13, T v13, String k14, T v14) {
        return new HashMap<String, T>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
            put(k4, v4);
            put(k5, v5);
            put(k6, v6);
            put(k7, v7);
            put(k8, v8);
            put(k9, v9);
            put(k10, v10);
            put(k11, v11);
            put(k12, v12);
            put(k13, v13);
            put(k14, v14);
        }};
    }
}
