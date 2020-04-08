package com.crowdin.client.users.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddProjectTeamMemberRequest {

    private List<Long> userIds;
    private boolean accessToAllWorkflowSteps;
    private boolean managerAccess;
    private Map<String, LanguagePermission> permissions;
}
