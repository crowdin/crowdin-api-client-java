package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * AI provider credentials. Superset of the credential fields of all provider types;
 * set only the fields required by the chosen provider type.
 */
@Data
public class Credentials {
    private String apiKey;
    private String baseUrl;
    private Map<String, String> headers;
    private Boolean sendCustomHeadersOnly;

    /**
     * Azure OpenAI
     */
    private String resourceName;
    private String deploymentName;
    private String apiVersion;

    /**
     * Google Gemini (Vertex AI)
     */
    private String project;
    private String region;
    private Map<String, Object> serviceAccountKey;
    private String workloadIdentityFederationAudience;
    private String serviceAccountEmail;

    /**
     * IBM Watsonx
     */
    private String projectId;

    /**
     * Microsoft Foundry
     */
    private List<Deployment> deployments;

    /**
     * Custom AI
     */
    private String identifier;
    private String key;

    @Data
    public static class Deployment {
        private String deploymentName;
        private String targetUri;
        private String apiKey;
    }
}
