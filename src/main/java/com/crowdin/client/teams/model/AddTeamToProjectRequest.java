package com.crowdin.client.teams.model;

import lombok.Data;

@Data
public class AddTeamToProjectRequest {

    private Long teamId;
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Object permissions;
}
