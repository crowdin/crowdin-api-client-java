package com.crowdin.client.notifications;

import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.notifications.model.SendNotificationToAuthenticatedUserRequest;
import com.crowdin.client.notifications.model.SendNotificationToOrganizationMembersByRoleRequest;
import com.crowdin.client.notifications.model.SendNotificationToOrganizationMembersByUserIdsRequest;
import com.crowdin.client.notifications.model.SendNotificationToOrganizationMembersRequest;
import com.crowdin.client.notifications.model.SendNotificationToProjectMemberRequest;
import com.crowdin.client.notifications.model.SendNotificationToProjectMemberByRoleRequest;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class NotificationsApiTest extends TestClient {
    private final Long projectId = 8L;
    private final List<Long> userIds = Arrays.asList(1L,2L);

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.buildWithRequestFileOnly(
                this.url + "/notify",
                HttpPost.METHOD_NAME,
                "api/notifications/notificationRequestToAuthenticatedUsers.json"
            ),
            RequestMock.buildWithRequestFileOnly(
                String.format("%s/projects/%d/notify",this.url,projectId),
                HttpPost.METHOD_NAME,
                "api/notifications/notificationRequestToProjectMembersByRole.json"
            ),
            RequestMock.buildWithRequestFileOnly(
                String.format("%s/notify", this.url),
                HttpPost.METHOD_NAME,
                "api/notifications/notificationRequestToOrganizationMembersByRole.json"
            ),
            RequestMock.buildWithRequestFileOnly(
                String.format("%s/notify", this.url),
                HttpPost.METHOD_NAME,
                "api/notifications/notificationRequestToOrganizationMembersByUserIds.json"
            )
        );
    }

    @Test
    public void sendNotificationToAuthenticatedUserTest(){
        SendNotificationToAuthenticatedUserRequest request = new SendNotificationToAuthenticatedUserRequest(){{
            setMessage("Hiii...");
        }};
        this.getNotificationsApi().sendNotificationToAuthenticatedUser(request);
    }


    @Test
    public void sendNotificationToProjectMembersByRoleTest(){
        SendNotificationToProjectMemberRequest request = new SendNotificationToProjectMemberByRoleRequest(){{
                setRole("owner");
                setMessage("Hiii...");
            }};
        this.getNotificationsApi().sendNotificationToProjectMembers(projectId, request);
    }

    @Test
    public void sendNotificationToOrganizationMembersByUserIdsTest() {
        SendNotificationToOrganizationMembersRequest request = new SendNotificationToOrganizationMembersByUserIdsRequest() {{
            setUserIds(userIds);
            setMessage("Hiii...");
        }};
        this.getNotificationsApi().sendNotificationToOrganizationMembers(request);
    }

    @Test
    public void sendNotificationToOrganizationMembersByRoleTest() {
        SendNotificationToOrganizationMembersRequest request = new SendNotificationToOrganizationMembersByRoleRequest() {{
            setRole("owner");
            setMessage("Hiii...");
        }};
        this.getNotificationsApi().sendNotificationToOrganizationMembers(request);
    }

}
