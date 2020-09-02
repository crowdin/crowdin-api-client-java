package com.crowdin.client.core.model;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.JsonTransformer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Additional configuration for client
 */
@Data
@Builder
public class ClientConfig {

    /**
     * Adds user agent to each request
     */
    private String userAgent;
    /**
     * Adds crowdin-specific user agent to each request
     */
    private String integrationUserAgent;
    /**
     * Provide custom http client
     */
    private HttpClient httpClient;
    /**
     * Provide custom json transformer
     */
    private JsonTransformer jsonTransformer;

    /**
     * Provide proxy host
     */
    private Host proxy;

    /**
     * Provide proxy credentials
     */
    private UsernamePasswordCredentials proxyCreds;


    @AllArgsConstructor
    @Data
    public final static class Host {
        private final String host;
        private final int port;
    }

    @AllArgsConstructor
    @Data
    public final static class UsernamePasswordCredentials {
        private final String username;
        private final String password;
    }
}
