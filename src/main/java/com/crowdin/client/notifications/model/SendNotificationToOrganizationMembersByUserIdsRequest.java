package com.crowdin.client.notifications.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SendNotificationToOrganizationMembersByUserIdsRequest extends SendNotificationToOrganizationMembersRequest {
    private List<Long> userIds;
}
