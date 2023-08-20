package com.crowdin.client.notifications;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.notifications.model.SendNotificationToAuthenticatedUserRequest;
import com.crowdin.client.notifications.model.SendNotificationToOrganizationMembersRequest;
import com.crowdin.client.notifications.model.SendNotificationToProjectMemberRequest;

public class NotificationsApi extends  CrowdinApi {
    public NotificationsApi(Credentials credentials) {
        super(credentials);
    }

    public NotificationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#tag/Notifications/api.projects.notification.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public void sendNotificationToAuthenticatedUser(SendNotificationToAuthenticatedUserRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/notify", this.url);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId Project Identifier
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.notify.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.notify.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void sendNotificationToProjectMembers(Long projectId, SendNotificationToProjectMemberRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/notify", this.url, projectId);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.notify.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void sendNotificationToOrganizationMembers(SendNotificationToOrganizationMembersRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/notify", this.url);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

}
