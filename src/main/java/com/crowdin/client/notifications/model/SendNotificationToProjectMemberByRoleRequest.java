package com.crowdin.client.notifications.model;

import lombok.Data;

@Data
public class SendNotificationToProjectMemberByRoleRequest extends SendNotificationToProjectMemberRequest {
    private String role;
    private String message;
}
