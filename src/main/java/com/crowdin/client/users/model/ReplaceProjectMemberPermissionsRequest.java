package com.crowdin.client.users.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReplaceProjectMemberPermissionsRequest {

    /**
     * @deprecated
     */
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    /**
     * @deprecated
     */
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
}
