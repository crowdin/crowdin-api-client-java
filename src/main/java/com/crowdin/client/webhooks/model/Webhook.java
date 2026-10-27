package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Webhook {

    private Long id;
    private Long projectId;
    private String name;
    private String url;
    private List<Event> events;
    /**
     * Webhook headers. The API returns an empty array ({@code []}) when the webhook has no headers;
     * it is represented as {@code null}, the same as a missing value.
     */
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
