package com.crowdin.client.core.http;

import com.crowdin.client.Client;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;

public class CrowdinApiConstructorTest extends Client {

    public CrowdinApiConstructorTest(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    public CrowdinApiConstructorTest(Credentials credentials) {
        super(credentials);
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public String geturl() {
        return this.url;
    }
}
