package com.crowdin.client.notifications.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SendNotificationToProjectMemberByUserIdsRequest extends SendNotificationToProjectMemberRequest {
    private List<Long> userIds;
    private String message;
}
