package com.crowdin.client.notifications.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SendNotificationToProjectMemberByRoleRequest extends SendNotificationToProjectMemberRequest {
    private String role;
    private String message;
}
