package com.crowdin.client.notifications.model;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRequestToOrganizationMembersByUserIds extends  NotificationRequestToOrganizationMembers{
    private List<Long> userIds;
    private String message;
}
