package com.crowdin.client.notifications;

import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.notifications.model.NotificationRequestToAuthenticatedUsers;
import com.crowdin.client.notifications.model.NotificationRequestToOrganizationMembers;
import com.crowdin.client.notifications.model.NotificationRequestToOrganizationMembersByRole;
import com.crowdin.client.notifications.model.NotificationRequestToOrganizationMembersByUserIds;
import com.crowdin.client.notifications.model.NotificationRequestToProjectMember;
import com.crowdin.client.notifications.model.NotificationRequestToProjectMemberByRole;
import com.crowdin.client.notifications.model.NotificationRequestToProjectMemberByUserIds;
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
        NotificationRequestToAuthenticatedUsers request = new NotificationRequestToAuthenticatedUsers(){{
            setMessage("Hiii...");
        }};
        this.getNotificationsApi().sendNotificationToAuthenticatedUser(request);
    }


    @Test
    public void sendNotificationToProjectMembersByRole(){
        NotificationRequestToProjectMember request = new NotificationRequestToProjectMemberByRole(){{
                setRole("Admin");
                setMessage("Hiii...");
            }};
        this.getNotificationsApi().sendNotificationToProjectMembers(projectId,request);
    }
}
