package com.crowdin.client.users.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReplaceProjectMemberPermissionsRequest {

    /**
     * @deprecated deprecated in the API, use {@link #roles} instead
     */
    @Deprecated
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Boolean developerAccess;
    /**
     * @deprecated deprecated in the API, use {@link #roles} instead
     */
    @Deprecated
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
}
