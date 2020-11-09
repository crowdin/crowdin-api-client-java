package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Map;

@Data
public class ReplaceProjectMemberPermissionsRequest {

    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Map<String, LanguagePermission> permissions;
}
