package com.crowdin.client.notifications.model;

import lombok.Data;

@Data
public class NotificationRequestToProjectMemberByRole extends NotificationRequestToProjectMember {
    private String role;
    private String message;
}
