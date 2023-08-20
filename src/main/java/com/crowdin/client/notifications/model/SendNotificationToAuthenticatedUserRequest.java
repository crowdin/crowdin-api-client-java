package com.crowdin.client.notifications.model;

import lombok.Data;

@Data
public class SendNotificationToAuthenticatedUserRequest {
    private String message;
}
