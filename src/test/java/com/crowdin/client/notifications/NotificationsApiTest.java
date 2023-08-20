package com.crowdin.client.notifications;

import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.notifications.model.SendNotificationToAuthenticatedUserRequest;
import com.crowdin.client.notifications.model.SendNotificationToProjectMemberRequest;
import com.crowdin.client.notifications.model.SendNotificationToProjectMemberByRoleRequest;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class NotificationsApiTest extends TestClient {
    private final Long projectId = 8L;
    private final List<Long> userIds = Arrays.asList(1l,2L);

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.buildWithRequestFileOnly(this.url + "/notify", HttpPost.METHOD_NAME,"api/notifications/notificationRequestToAuthenticatedUsers.json"),
                RequestMock.buildWithRequestFileOnly(String.format("%s/projects/%d/notify",this.url,projectId), HttpPost.METHOD_NAME, "api/notifications/notificationRequestToProjectMembersByRole.json")
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
    public void sendNotificationToProjectMembersByRole(){
        SendNotificationToProjectMemberRequest request = new SendNotificationToProjectMemberByRoleRequest(){{
                setRole("owner");
                setMessage("Hiii...");
            }};
        this.getNotificationsApi().sendNotificationToProjectMembers(projectId,request);
    }
}
