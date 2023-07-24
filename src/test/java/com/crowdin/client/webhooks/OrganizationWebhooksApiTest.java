package com.crowdin.client.webhooks;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.webhooks.model.AddOrgWebhookRequest;
import com.crowdin.client.webhooks.model.ContentType;
import com.crowdin.client.webhooks.model.OrganizationEvent;
import com.crowdin.client.webhooks.model.OrganizationWebhook;
import com.crowdin.client.webhooks.model.RequestType;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationWebhooksApiTest extends TestClient {

    private final String baseUrl = "/webhooks";
    private final Long organizationWebhookId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        baseUrl,
                        HttpGet.METHOD_NAME,
                        "api/webhooks/organization/commonResponses_multi.json",
                        new HashMap<String, Integer>() {{
                            put("limit", 20);
                            put("offset", 10);
                        }}
                ),
                // ADD
                RequestMock.build(
                        baseUrl,
                        HttpPost.METHOD_NAME,
                        "api/webhooks/organization/addWebhookRequest.json",
                        "api/webhooks/organization/commonResponses_single.json"
                ),
                // GET
                RequestMock.build(
                        formUrl_orgWebhookId(organizationWebhookId),
                        HttpGet.METHOD_NAME,
                        "api/webhooks/organization/commonResponses_single.json"
                ),
                // DELETE
                RequestMock.build(
                        formUrl_orgWebhookId(organizationWebhookId),
                        HttpDelete.METHOD_NAME
                ),
                // EDIT
                RequestMock.build(
                        formUrl_orgWebhookId(organizationWebhookId),
                        HttpPatch.METHOD_NAME,
                        "api/webhooks/organization/editWebhookRequest.json",
                        "api/webhooks/organization/commonResponses_single.json"
                )
        );
    }

    private String formUrl_orgWebhookId(Long organizationWebhookId) {
        return this.url + "/webhooks/" + organizationWebhookId;
    }

    @Test
    public void listWebhooksTest() {
        ResponseList<OrganizationWebhook> responseList = this.getWebhooksApi().listOrgWebhooks(20, 10);
        assertWebhook(responseList.getData().get(0).getData());
    }

    @Test
    public void addWebhookTest() {
        AddOrgWebhookRequest request = new AddOrgWebhookRequest() {{
            setName("Proofread");
            setUrl("https://test.com");
            setEvents(new ArrayList<OrganizationEvent>() {{
                add(OrganizationEvent.GROUP_CREATED);
                add(OrganizationEvent.GROUP_DELETED);
                add(OrganizationEvent.PROJECT_CREATED);
                add(OrganizationEvent.PROJECT_DELETED);
            }});
            setRequestType(RequestType.POST);
            setIsActive(true);
            setBatchingEnabled(true);
            setContentType(ContentType.APPLICATION_JSON);
            setHeaders(new HashMap<String, String>() {{
                put("Authorization", "key");
            }});
            // setPayload();
        }};

        ResponseObject<OrganizationWebhook> response = this.getWebhooksApi().addOrgWebhook(request);
        assertWebhook(response.getData());
    }

    @Test
    public void getWebhookTest() {
        ResponseObject<OrganizationWebhook> response = this.getWebhooksApi().getOrgWebhook(organizationWebhookId);
        assertWebhook(response.getData());
    }

    @Test
    public void deleteWebhookTest() {
        this.getWebhooksApi().deleteOrgWebhook(organizationWebhookId);
    }

    @Test
    public void editWebhookTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/name");
                setValue("Proofread");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/events");
                setValue(Arrays.asList(
                        OrganizationEvent.PROJECT_CREATED,
                        OrganizationEvent.PROJECT_DELETED
                ));
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/contentType");
                setValue("application/json");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/requestType");
                setValue(RequestType.POST);
            }});
        }};

        ResponseObject<OrganizationWebhook> response = this.getWebhooksApi().editOrgWebhook(organizationWebhookId, request);
        assertWebhook(response.getData());
    }

    @SneakyThrows
    private static void assertWebhook(OrganizationWebhook webhook) {
        assertNotNull(webhook);

        assertEquals(4, webhook.getId());
        assertEquals("Proofread", webhook.getName());
        assertEquals("https://test.com", webhook.getUrl());
        assertEquals(RequestType.GET, webhook.getRequestType());
        assertEquals(ContentType.APPLICATION_JSON, webhook.getContentType());
        assertTrue(webhook.getIsActive());
        assertTrue(webhook.getBatchingEnabled());

        assertNotNull(webhook.getHeaders());
        assertTrue(webhook.getHeaders().isEmpty());

        assertNotNull(webhook.getEvents());
        assertFalse(webhook.getEvents().isEmpty());
    }
}
