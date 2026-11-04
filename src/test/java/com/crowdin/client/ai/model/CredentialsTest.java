package com.crowdin.client.ai.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CredentialsTest {

    private static final String API_KEY = "sk-api-key-secret";
    private static final String HEADER_TOKEN = "Bearer header-token-secret";
    private static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----service-account-secret";
    private static final String CUSTOM_KEY = "custom-ai-key-secret";
    private static final String DEPLOYMENT_KEY = "foundry-deployment-key-secret";
    private static final String[] SECRETS = {API_KEY, HEADER_TOKEN, PRIVATE_KEY, CUSTOM_KEY, DEPLOYMENT_KEY};

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void toStringDoesNotExposeSecretsTest() {
        Credentials credentials = credentials();
        AiProviderRequest request = new AiProviderRequest();
        request.setName("provider");
        request.setType("open_ai");
        request.setCredentials(credentials);
        AiProvider provider = new AiProvider();
        provider.setCredentials(credentials);

        for (String text : new String[]{credentials.toString(), credentials.getDeployments().get(0).toString(),
                request.toString(), provider.toString()}) {
            for (String secret : SECRETS) {
                assertFalse(text.contains(secret), "secret leaked in toString: " + text);
            }
        }
        assertTrue(credentials.toString().contains("https://proxy.example.com"));
        assertTrue(credentials.toString().contains("my-gcp-project"));
        assertTrue(credentials.getDeployments().get(0).toString().contains("https://foundry.example.com"));
    }

    @Test
    void serializationStillIncludesCredentialsTest() {
        String json = jsonTransformer.convert(credentials());
        for (String secret : SECRETS) {
            assertTrue(json.contains(secret), "secret missing from request JSON: " + secret);
        }
        Credentials parsed = jsonTransformer.parse(json, Credentials.class);
        assertEquals(API_KEY, parsed.getApiKey());
        assertEquals(HEADER_TOKEN, parsed.getHeaders().get("Authorization"));
        assertEquals(PRIVATE_KEY, parsed.getServiceAccountKey().get("private_key"));
        assertEquals(CUSTOM_KEY, parsed.getKey());
        assertEquals(DEPLOYMENT_KEY, parsed.getDeployments().get(0).getApiKey());
        assertEquals(credentials(), parsed);
    }

    @Test
    void secretsStillTakePartInEqualsTest() {
        Credentials other = credentials();
        other.setApiKey("another-key");
        assertNotEquals(credentials(), other);
    }

    private static Credentials credentials() {
        Credentials credentials = new Credentials();
        credentials.setApiKey(API_KEY);
        credentials.setBaseUrl("https://proxy.example.com");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Authorization", HEADER_TOKEN);
        credentials.setHeaders(headers);
        credentials.setSendCustomHeadersOnly(true);
        credentials.setProject("my-gcp-project");
        credentials.setRegion("us-central1");
        Map<String, Object> serviceAccountKey = new LinkedHashMap<>();
        serviceAccountKey.put("type", "service_account");
        serviceAccountKey.put("private_key", PRIVATE_KEY);
        credentials.setServiceAccountKey(serviceAccountKey);
        credentials.setIdentifier("custom-ai-identifier");
        credentials.setKey(CUSTOM_KEY);
        Credentials.Deployment deployment = new Credentials.Deployment();
        deployment.setDeploymentName("gpt-deployment");
        deployment.setTargetUri("https://foundry.example.com");
        deployment.setApiKey(DEPLOYMENT_KEY);
        credentials.setDeployments(Collections.singletonList(deployment));
        return credentials;
    }
}
