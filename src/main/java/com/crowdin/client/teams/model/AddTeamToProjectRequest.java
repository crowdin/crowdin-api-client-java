package com.crowdin.client.teams.model;

import com.crowdin.client.users.model.TranslatorRole;
import lombok.Data;

import java.util.List;

@Data
public class AddTeamToProjectRequest {

    private Long teamId;
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Object permissions;
    private List<TranslatorRole> roles;
}
