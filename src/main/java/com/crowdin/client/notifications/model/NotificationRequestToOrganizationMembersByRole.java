package com.crowdin.client.notifications.model;

import lombok.Data;

@Data
public class NotificationRequestToOrganizationMembersByRole extends  NotificationRequestToOrganizationMembers{
    private String role;
    private String message;
}
