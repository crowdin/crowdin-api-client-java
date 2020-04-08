package com.crowdin.client.webhooks.model;

import lombok.Data;

import java.util.List;

@Data
public class AddWebhookRequest {

    private String name;
    private String url;
    private List<Event> events;
    private RequestType requestType;
    private boolean isActive;
    private ContentType contentType;
    private Object headers;
    private Object payload;
}
