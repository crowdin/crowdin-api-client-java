package com.crowdin.client.teams.model;

import com.crowdin.client.users.model.TranslatorRole;
import lombok.Data;

import java.util.List;

@Data
public class ProjectTeamResource {

    private Long id;
    private boolean hasManagerAccess;
    private boolean hasAccessToAllWorkflowSteps;
    private Object permissions;
    private List<TranslatorRole> roles;
}
