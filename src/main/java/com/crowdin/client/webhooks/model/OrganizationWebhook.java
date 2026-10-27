package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class OrganizationWebhook {
    private Long id;
    private String name;
    private String url;
    private List<OrganizationEvent> events;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, String> headers;
    private Object payload;
    private Boolean isActive;
    private Boolean batchingEnabled;
    private RequestType requestType;
    private ContentType contentType;
    private Date createdAt;
    private Date updatedAt;
}
