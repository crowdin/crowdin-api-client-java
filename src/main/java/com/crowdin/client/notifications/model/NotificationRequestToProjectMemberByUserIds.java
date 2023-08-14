package com.crowdin.client.notifications.model;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRequestToProjectMemberByUserIds extends NotificationRequestToProjectMember {
    private List<Long> userIds;
    private String message;
}
