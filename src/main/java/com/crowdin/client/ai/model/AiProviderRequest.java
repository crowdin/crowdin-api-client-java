package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class AiProviderRequest {

    private String name;
    private String type;
    private Credentials credentials;
    /**
     * @deprecated deprecated in the API
     */
    @Deprecated
    private Config config;
    private Boolean isEnabled;
    private Boolean useSystemCredentials;
}
