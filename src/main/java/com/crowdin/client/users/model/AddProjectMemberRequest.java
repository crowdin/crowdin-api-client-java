package com.crowdin.client.users.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddProjectMemberRequest {

    private List<Long> userIds;
    private String usernames;
    private List<String> emails;
    /**
     * @deprecated
     */
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Boolean developerAccess;
    /**
     * @deprecated
     */
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
}
