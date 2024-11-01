package com.crowdin.client.notifications.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SendNotificationToOrganizationMembersByRoleRequest extends SendNotificationToOrganizationMembersRequest {
    private String role;
}
