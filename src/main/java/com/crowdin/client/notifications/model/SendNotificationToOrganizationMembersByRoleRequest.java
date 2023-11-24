package com.crowdin.client.notifications.model;

import lombok.Data;

@Data
public class SendNotificationToOrganizationMembersByRoleRequest extends SendNotificationToOrganizationMembersRequest {
    private String role;
}
