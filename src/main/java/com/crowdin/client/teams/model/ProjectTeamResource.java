package com.crowdin.client.teams.model;

import lombok.Data;

@Data
public class ProjectTeamResource {

    private Long id;
    private boolean hasManagerAccess;
    private boolean hasAccessToAllWorkflowSteps;
    private Object permissions;
}
