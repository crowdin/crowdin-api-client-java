package com.crowdin.client.teams.model;

import lombok.Data;

@Data
public class ProjectTeamResources {

    private ProjectTeamResource skipped;
    private ProjectTeamResource updated;
    private ProjectTeamResource added;
}
