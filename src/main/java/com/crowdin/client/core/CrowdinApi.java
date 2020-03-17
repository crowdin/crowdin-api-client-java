package com.crowdin.client.core;

import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.impl.JacksonJsonTransformer;
import com.crowdin.client.core.http.impl.ApacheHttpClient;

public abstract class CrowdinApi {

    protected final HttpClient httpClient;
    protected final String url;

    public CrowdinApi(Credentials credentials) {
        this(credentials, new ApacheHttpClient(credentials, new JacksonJsonTransformer()));
    }

    public CrowdinApi(Credentials credentials, JsonTransformer jsonTransformer) {
        this(credentials, new ApacheHttpClient(credentials, jsonTransformer));
    }

    public CrowdinApi(Credentials credentials, HttpClient httpClient) {
        this.httpClient = httpClient;
        if (credentials.getOrganization() != null) {
            this.url = "https://" + credentials.getOrganization() + ".crowdin.com/api/v2";
        } else {
            this.url = "https://api.crowdin.com/api/v2";
        }
    }
}
