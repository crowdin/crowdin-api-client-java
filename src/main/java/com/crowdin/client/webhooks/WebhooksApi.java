package com.crowdin.client.webhooks;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.webhooks.model.AddWebhookRequest;
import com.crowdin.client.webhooks.model.Webhook;
import com.crowdin.client.webhooks.model.WebhookResponseList;
import com.crowdin.client.webhooks.model.WebhookResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WebhooksApi extends CrowdinApi {
    public WebhooksApi(Credentials credentials) {
        super(credentials);
    }

    public WebhooksApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of webhooks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.webhooks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.webhooks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Webhook> listWebhooks(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        WebhookResponseList webhookResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/webhooks", new HttpRequestConfig(queryParams), WebhookResponseList.class);
        return WebhookResponseList.to(webhookResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created webhook
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.webhooks.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.webhooks.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Webhook> addWebhook(Long projectId, AddWebhookRequest request) throws HttpException, HttpBadRequestException {
        WebhookResponseObject webhookResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/webhooks", request, new HttpRequestConfig(), WebhookResponseObject.class);
        return ResponseObject.of(webhookResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param webhookId webhook identifier
     * @return webhook
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.webhooks.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.webhooks.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Webhook> getWebhook(Long projectId, Long webhookId) throws HttpException, HttpBadRequestException {
        WebhookResponseObject webhookResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, new HttpRequestConfig(), WebhookResponseObject.class);
        return ResponseObject.of(webhookResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param webhookId webhook identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.webhooks.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.webhooks.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteWebhook(Long projectId, Long webhookId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param webhookId webhook identifier
     * @param request request object
     * @return updated webhook
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.webhooks.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.webhooks.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Webhook> editWebhook(Long projectId, Long webhookId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        WebhookResponseObject webhookResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/webhooks/" + webhookId, request, new HttpRequestConfig(), WebhookResponseObject.class);
        return ResponseObject.of(webhookResponseObject.getData());
    }
}
