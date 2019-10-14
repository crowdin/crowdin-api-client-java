package crowdin.api.client.core;

import crowdin.api.client.core.model.Credentials;
import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.http.impl.JacksonJsonTransformer;
import crowdin.api.client.core.http.impl.JavaHttpClient;

public abstract class CrowdinApi {

    protected final HttpClient httpClient;
    protected final String url;

    public CrowdinApi(Credentials credentials) {
        this(credentials, new JavaHttpClient(credentials, new JacksonJsonTransformer()));
    }

    public CrowdinApi(Credentials credentials, JsonTransformer jsonTransformer) {
        this(credentials, new JavaHttpClient(credentials, jsonTransformer));
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
