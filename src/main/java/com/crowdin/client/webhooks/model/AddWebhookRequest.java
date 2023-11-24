package com.crowdin.client.webhooks.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddWebhookRequest {

    private String name;
    private String url;
    private List<Event> events;
    private RequestType requestType;
    private Boolean isActive;
    private Boolean batchingEnabled;
    private ContentType contentType;
    private Map<String, String> headers;
    private Object payload;
}
