package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ProjectTeamMember {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isManager;
    private ManagerOfGroup managerOfGroup;
    private boolean accessToAllWorkflowSteps;
    private Map<String, LanguagePermission> permissions;
    private Date lastSeen;
}
