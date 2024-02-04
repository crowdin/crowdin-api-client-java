package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ProjectMember {

    private Long id;
    private String username;
    private String fullName;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private boolean isManager;
    private boolean isDeveloper;
    private ManagerOfGroup managerOfGroup;
    /**
     * @deprecated
     */
    private boolean accessToAllWorkflowSteps;
    /**
     * @deprecated
     */
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
    private String role;
    private String timezone;
    private Date joinedAt;
    private Date givenAccessAt;
}
