package com.crowdin.client.notifications.model;

import lombok.Data;

import java.util.List;

@Data
public class SendNotificationToOrganizationMembersByUserIdsRequest extends SendNotificationToOrganizationMembersRequest {
    private List<Long> userIds;
    private String message;
}
