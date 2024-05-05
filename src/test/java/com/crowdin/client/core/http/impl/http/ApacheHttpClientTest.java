package com.crowdin.client.core.http.impl.http;

import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.labels.model.Label;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApacheHttpClientTest {

    private final Long projectId = 1L;
    private final Long reportSettingsTemplateId = 2L;

    private final String organizationUrl = "https://testOrganization.api.crowdin.com/api/v2" + "/projects/" + projectId + "/settings-templates/" + reportSettingsTemplateId;

    private CloseableHttpClient client;
    private CloseableHttpResponse mockedResponse;

    private ApacheHttpClient httpClient;

    private final String json = "{\n" +
            "  \"id\": 123456789,\n" +
            "  \"title\": \"Example Label\",\n" +
            "  \"isSystem\": {\n" +
            "    \"value\": true\n" +
            "  }\n" +
            "}\n";


    @BeforeEach
    void setUp() {
        String organization = "testOrganization";
        String token = "testToken";

        Credentials credentials = new Credentials(token, organization);
        JsonTransformer jsonTransformer = new JacksonJsonTransformer();
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("user-agent", "Mozilla/5.0");

        client = mock(CloseableHttpClient.class);
        mockedResponse = mock(CloseableHttpResponse.class);

        // Create an instance of ApacheHttpClient
        httpClient = new ApacheHttpClient(credentials, jsonTransformer, defaultHeaders, client);
    }

    @Test
    public void testGetRequest() throws IOException {
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity(json));

        // Make a GET request and check the response
        try {
            String response = httpClient.get(organizationUrl, new HttpRequestConfig(), String.class);
            // Assert that the response is not null
            Assertions.assertNotNull(response);
            assertEquals(response, json);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetRequestError() throws IOException {
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "FAIL"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity("{\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"error\": {\n" +
                "        \"key\": \"400\",\n" +
                "        \"errors\": [\n" +
                "          {\n" +
                "            \"code\": \"400\",\n" +
                "            \"message\": \"Bad request exception!\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n"));


        // Make a GET request and check the exception
        assertThrows(HttpBadRequestException.class, () -> httpClient.get(organizationUrl, new HttpRequestConfig(), String.class));
    }

    @Test
    public void testDeleteRequest() throws IOException {
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity("Mocked response body"));


        // Make a DELETE request and check the response
        try {
            httpClient.delete(organizationUrl, new HttpRequestConfig(), Void.class);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void tesPostRequest() throws IOException {
        Label requestData = new Label();
        requestData.setId(1L);
        requestData.setTitle("Test");
        requestData.setIsSystem(BooleanInt.TRUE);
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity(json));

        // Make a POST request and check the response
        try {
            String response = httpClient.post(organizationUrl, requestData, new HttpRequestConfig(), String.class);
            assertNotNull(response);
            assertEquals(response, json);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void tesPostRequestWithInputStream() throws IOException {
        InputStream requestData = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity(json));

        // Make a POSt request and check the response
        try {
            String response = httpClient.post(organizationUrl, requestData, new HttpRequestConfig(), String.class);
            Assertions.assertNotNull(response);
            assertEquals(response, json);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void tesPostRequestWithString() throws IOException {
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity(json));

        // Make a POST request and check the response
        try {
            String response = httpClient.post(organizationUrl, json, new HttpRequestConfig(), String.class);
            Assertions.assertNotNull(response);
            assertEquals(response, json);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void tesPostRequestWithNullData() throws IOException {
        // Mock the behavior of client.execute to return mockedResponse
        when(client.execute(any(HttpUriRequest.class))).thenReturn(mockedResponse);
        when(mockedResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
        when(mockedResponse.getEntity()).thenReturn(new StringEntity(json));

        // Make a POST request and check the response
        try {
            String response = httpClient.post(organizationUrl, null, new HttpRequestConfig(), String.class);
            Assertions.assertNotNull(response);
            assertEquals(response, json);
        } catch (HttpException | HttpBadRequestException e) {
            e.printStackTrace();
            Assertions.fail("HTTP request failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }
}
