package com.crowdin.client.core.http;

import com.crowdin.client.core.http.impl.http.ApacheHttpClient;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.framework.TestHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrowdinApiTest {

    private final String organization = "testOrganization";
    private final String token = "testToken";
    private final String regularUrl = "https://api.crowdin.com/api/v2";
    private final String organizationUrl = "https://testOrganization.api.crowdin.com/api/v2";

    @Test
    public void testCrowdinApiWithNullInputs() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(null, null, null), ClientConfig.builder()
                .userAgent("User-Agent")
                .build());
        assertNotNull(client);
        assertInstanceOf(ApacheHttpClient.class, client.getHttpClient());
    }

    @Test
    public void testCrowdinApiWithAgent() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(null, null, null), ClientConfig.builder()
                .integrationUserAgent("X-Crowdin-Integrations-User-Agent")
                .jsonTransformer(new JacksonJsonTransformer())
                .build());
        assertNotNull(client);
        assertInstanceOf(ApacheHttpClient.class, client.getHttpClient());
        assertEquals(client.geturl(), regularUrl);
    }

    @Test
    public void testCrowdinApiWithBaseUrl() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(token, organization, "/"), ClientConfig.builder()
                .httpClient(new TestHttpClient()).build());
        assertEquals(client.geturl(), "/api/v2");
    }

    @Test
    public void testCrowdinApiWithBaseUrlEmpty() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(token, organization, ""), ClientConfig.builder()
                .userAgent("User-Agent")
                .httpClient(new TestHttpClient()).build());
        assertEquals(client.geturl(), "/api/v2");
    }

    @Test
    public void testCrowdinApiForRegularUrl() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(token, null, null), ClientConfig.builder()
                .userAgent("User-Agent")
                .httpClient(new TestHttpClient()).build());
        assertEquals(client.geturl(), regularUrl);
    }

    @Test
    public void testCrowdinApiForOrganizationUrl() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(token, organization, null), ClientConfig.builder()
                .userAgent("User-Agent")
                .httpClient(new TestHttpClient()).build());
        assertEquals(client.geturl(), organizationUrl);
    }

    @Test
    public void testCrowdinApiConstructor() {
        CrowdinApiConstructorTest client = new CrowdinApiConstructorTest(new Credentials(token, organization, null));
        assertInstanceOf(ApacheHttpClient.class, client.getHttpClient());
        assertEquals(client.geturl(), organizationUrl);
    }

}
