package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ProjectMember {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isManager;
    private ManagerOfGroup managerOfGroup;
    private boolean accessToAllWorkflowSteps;
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
    private Date lastSeen;
}
