package com.crowdin.client.webhooks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Webhook {

    private Long id;
    private Long projectId;
    private String name;
    private String url;
    private List<Event> events;
    private List<String> headers;
    private List<String> payload;
    private Boolean isActive;
    private Boolean batchingEnabled;
    private RequestType requestType;
    private ContentType contentType;
    private Date createdAt;
    private Date updatedAt;
}
