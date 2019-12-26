package com.crowdin.common.models;

import java.util.List;

public class Webhook {

    private String name;
    private String url;
    private boolean isActive;
    private String contentType;
    private List<String> events;
    private String headers;
    private String requestType;
    private String payload;

    public Webhook(String name,
                   String url,
                   boolean isActive,
                   String contentType,
                   List<String> events,
                   String headers,
                   String requestType,
                   String payload) {
        this.name = name;
        this.url = url;
        this.isActive = isActive;
        this.contentType = contentType;
        this.events = events;
        this.headers = headers;
        this.requestType = requestType;
        this.payload = payload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
