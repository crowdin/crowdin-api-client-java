package com.crowdin.client.core;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.impl.ApacheHttpClient;
import com.crowdin.client.core.http.impl.JacksonJsonTransformer;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;

public abstract class CrowdinApi {

    protected final HttpClient httpClient;
    protected final ClientConfig clientConfig;
    protected final String url;

    public CrowdinApi(Credentials credentials) {
        this(credentials, ClientConfig.builder().httpClient(new ApacheHttpClient(credentials, new JacksonJsonTransformer())).build());
    }

    public CrowdinApi(Credentials credentials, ClientConfig clientConfig) {
        if (clientConfig.getJsonTransformer() != null && clientConfig.getHttpClient() == null) {
            this.httpClient = new ApacheHttpClient(credentials, clientConfig.getJsonTransformer());
        } else {
            this.httpClient = clientConfig.getHttpClient();
        }
        this.clientConfig = clientConfig;
        if (credentials.getOrganization() != null) {
            this.url = "https://" + credentials.getOrganization() + ".api.crowdin.com/api/v2";
        } else {
            this.url = "https://api.crowdin.com/api/v2";
        }
    }
}
