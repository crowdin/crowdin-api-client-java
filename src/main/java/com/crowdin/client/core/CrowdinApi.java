package com.crowdin.client.core;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.impl.http.ApacheHttpClient;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.CrowdinUrlBuilder;
import com.crowdin.client.core.model.UrlBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class CrowdinApi {

    protected final HttpClient httpClient;
    protected final ClientConfig clientConfig;
    protected final String url;

    public CrowdinApi(Credentials credentials) {
        this(credentials, ClientConfig.builder().httpClient(new ApacheHttpClient(credentials, new JacksonJsonTransformer(), Collections.emptyMap())).build());
    }

    public CrowdinApi(Credentials credentials, ClientConfig clientConfig) {
        if (clientConfig.getJsonTransformer() != null && clientConfig.getHttpClient() == null) {
            Map<String, String> defaultHeaders = new HashMap<>();
            if (clientConfig.getUserAgent() != null) {
                defaultHeaders.put("User-Agent", clientConfig.getUserAgent());
            }
            if (clientConfig.getIntegrationUserAgent() != null) {
                defaultHeaders.put("X-Crowdin-Integrations-User-Agent", clientConfig.getIntegrationUserAgent());
            }
            this.httpClient = new ApacheHttpClient(credentials, clientConfig.getJsonTransformer(), defaultHeaders);
        } else {
            this.httpClient = clientConfig.getHttpClient();
        }
        this.clientConfig = clientConfig;
        UrlBuilder urlBuilder = clientConfig.getUrlBuilder() != null
            ? clientConfig.getUrlBuilder()
            : new CrowdinUrlBuilder();
        if (credentials.getOrganization() != null) {
            this.url = urlBuilder.getWithOrganization(credentials.getOrganization());
        } else {
            this.url = urlBuilder.get();
        }
    }
}
