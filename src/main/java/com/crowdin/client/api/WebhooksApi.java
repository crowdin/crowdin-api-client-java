package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Reference;
import com.crowdin.common.models.Webhook;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class WebhooksApi extends Api {

    public WebhooksApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Webhook>> getWebhooks(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Webhook>>() {
        })
                .path(Path.WEBHOOKS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Webhook>> createWebhook(String userId, String projectId, Webhook webhook) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Webhook>>() {
        })
                .path(Path.WEBHOOKS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(webhook);
    }

    public CrowdinRequestBuilder<SimpleResponse<Webhook>> getWebhook(String userId, String projectId, String webhookId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Webhook>>() {
        })
                .path(Path.WEBHOOK)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, webhookId);
    }

    public CrowdinRequestBuilder<String> deleteProject(String userId, String projectId, String webhookId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.WEBHOOK)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, webhookId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Reference>> updateProject(String userId, String projectId, String webhookId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Reference>>() {
        })
                .path(Path.WEBHOOK)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, webhookId)
                .requestBody(updateOperations);
    }
}
