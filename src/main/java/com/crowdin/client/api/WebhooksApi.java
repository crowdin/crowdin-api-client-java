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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class WebhooksApi extends Api {

    public WebhooksApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Webhook>> getWebhooks(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Webhook>>() {
        })
                .path(Path.WEBHOOKS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Webhook>> createWebhook(String projectId, Webhook webhook) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Webhook>>() {
        })
                .path(Path.WEBHOOKS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(webhook);
    }

    public CrowdinRequestBuilder<SimpleResponse<Webhook>> getWebhook(String projectId, String webhookId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Webhook>>() {
        })
                .path(Path.WEBHOOK)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, webhookId);
    }

    public CrowdinRequestBuilder deleteProject(String projectId, String webhookId) {
        return getBuilderWithSettings()
                .path(Path.WEBHOOK)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, webhookId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Reference>> updateProject(String projectId, String webhookId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Reference>>() {
        })
                .path(Path.WEBHOOK)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, webhookId)
                .requestBody(updateOperations);
    }
}
