package com.crowdin.client.ai.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * AI provider credentials. Superset of the credential fields of all provider types; set only the fields of the
 * chosen provider type (the SDK does not validate the combination, the API does).
 * <p>
 * Supported provider types and their credential fields (required fields in bold):
 * <ul>
 *     <li>{@code open_ai}, {@code google_gemini_ai_studio}, {@code mistral_ai}, {@code anthropic}, {@code x_ai},
 *     {@code deepseek}: <b>apiKey</b>, baseUrl, headers, sendCustomHeadersOnly</li>
 *     <li>{@code azure_open_ai}: <b>resourceName</b>, <b>apiKey</b>, <b>deploymentName</b>, <b>apiVersion</b>, baseUrl,
 *     headers, sendCustomHeadersOnly</li>
 *     <li>{@code google_gemini} (Vertex AI): <b>project</b>, <b>region</b>, serviceAccountKey or
 *     workloadIdentityFederationAudience (one of them is required), serviceAccountEmail, baseUrl, headers,
 *     sendCustomHeadersOnly</li>
 *     <li>{@code watsonx}: <b>apiKey</b>, <b>projectId</b>, <b>region</b>, baseUrl, headers, sendCustomHeadersOnly</li>
 *     <li>{@code microsoft_foundry}: <b>deployments</b> (each with <b>deploymentName</b>, <b>targetUri</b>,
 *     <b>apiKey</b>), headers, sendCustomHeadersOnly</li>
 *     <li>{@code custom_ai}: <b>identifier</b>, <b>key</b></li>
 * </ul>
 * Secret values ({@code apiKey}, {@code headers}, {@code serviceAccountKey}, {@code key} and the deployments' API keys)
 * are sent to the API as usual but are excluded from {@code toString()}.
 */
@Data
public class Credentials {
    @ToString.Exclude
    private String apiKey;
    /**
     * Override the provider API base URL (e.g. a self-hosted proxy or third-party gateway)
     */
    private String baseUrl;
    /**
     * Custom HTTP headers sent with every request to the provider; may contain authorization values
     */
    @ToString.Exclude
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
    /**
     * Google Gemini (Vertex AI) service account key (contains a private key)
     */
    @ToString.Exclude
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
    @ToString.Exclude
    private String key;

    /**
     * Microsoft Foundry deployment
     */
    @Data
    public static class Deployment {
        private String deploymentName;
        private String targetUri;
        @ToString.Exclude
        private String apiKey;
    }
}
