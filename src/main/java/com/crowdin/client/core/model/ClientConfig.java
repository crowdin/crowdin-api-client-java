package com.crowdin.client.core.model;

import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.JsonTransformer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientConfig {

    private String userAgent;
    private String integrationUserAgent;
    private HttpClient httpClient;
    private JsonTransformer jsonTransformer;
}
