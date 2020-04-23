package com.crowdin.client.teams.model;

import lombok.Data;

@Data
public class AddTeamToProjectRequest {

    private Long teamId;
    private boolean accessToAllWorkflowSteps;
    private boolean managerAccess;
    private Object permissions;
}
