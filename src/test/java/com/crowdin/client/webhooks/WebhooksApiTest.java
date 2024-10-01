package com.crowdin.client.webhooks;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.webhooks.model.AddWebhookRequest;
import com.crowdin.client.webhooks.model.ContentType;
import com.crowdin.client.webhooks.model.Event;
import com.crowdin.client.webhooks.model.RequestType;
import com.crowdin.client.webhooks.model.Webhook;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebhooksApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long webhookId = 4L;
    private final Boolean batchingEnabled = false;
    private final String name = "Proofread";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks", HttpGet.METHOD_NAME, "api/webhooks/listWebhooks.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks", HttpPost.METHOD_NAME, "api/webhooks/addWebhookRequest.json", "api/webhooks/webhook.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks", HttpPost.METHOD_NAME, "api/webhooks/addWebhookRequestRawEvent.json", "api/webhooks/webhookRawEvent.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, HttpGet.METHOD_NAME, "api/webhooks/webhook.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, HttpPatch.METHOD_NAME, "api/webhooks/editWebhook.json", "api/webhooks/webhook.json")
        );
    }

    @Test
    public void listWebhooksTest() {
        ResponseList<Webhook> webhookResponseList = this.getWebhooksApi().listWebhooks(projectId, null, null);
        assertEquals(webhookResponseList.getData().size(), 1);
        assertEquals(webhookResponseList.getData().get(0).getData().getId(), webhookId);
        assertEquals(webhookResponseList.getData().get(0).getData().getName(), name);
        assertEquals(webhookResponseList.getData().get(0).getData().getBatchingEnabled(), batchingEnabled);
    }

    @Test
    public void addWebhookTest() {
        AddWebhookRequest request = new AddWebhookRequest();
        request.setName(name);
        request.setUrl("test.com");
        request.setEvents(singletonList(Event.FILE_APPROVED));
        request.setRequestType(RequestType.POST);
        request.setIsActive(true);
        request.setBatchingEnabled(batchingEnabled);
        request.setContentType(ContentType.MULTIPART_FORM_DATA);
        ResponseObject<Webhook> webhookResponseObject = this.getWebhooksApi().addWebhook(projectId, request);
        assertEquals(webhookResponseObject.getData().getId(), webhookId);
        assertEquals(webhookResponseObject.getData().getName(), name);
    }

    @Test
    public void addWebhookRawEventTest() {
        AddWebhookRequest request = new AddWebhookRequest();
        List<Event> events = Stream.of("stringComment.created").map(Event::from).collect(Collectors.toList());
        request.setName(name);
        request.setUrl("test.com");
        request.setEvents(events);
        request.setRequestType(RequestType.POST);
        request.setIsActive(true);
        request.setBatchingEnabled(batchingEnabled);
        request.setContentType(ContentType.MULTIPART_FORM_DATA);
        ResponseObject<Webhook> webhookResponseObject = this.getWebhooksApi().addWebhook(projectId, request);
        assertEquals(webhookResponseObject.getData().getId(), webhookId);
        assertEquals(webhookResponseObject.getData().getName(), name);
        assertEquals(events, webhookResponseObject.getData().getEvents());
    }

    @Test
    public void getWebhookTest() {
        ResponseObject<Webhook> webhookResponseObject = this.getWebhooksApi().getWebhook(projectId, webhookId);
        assertEquals(webhookResponseObject.getData().getId(), webhookId);
        assertEquals(webhookResponseObject.getData().getName(), name);
        assertEquals(webhookResponseObject.getData().getBatchingEnabled(), batchingEnabled);
    }

    @Test
    public void deleteWebhookTest() {
        this.getWebhooksApi().deleteWebhook(projectId, webhookId);
    }

    @Test
    public void editWebhookTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Webhook> webhookResponseObject = this.getWebhooksApi().editWebhook(projectId, webhookId, singletonList(request));
        assertEquals(webhookResponseObject.getData().getId(), webhookId);
        assertEquals(webhookResponseObject.getData().getName(), name);
    }

}
