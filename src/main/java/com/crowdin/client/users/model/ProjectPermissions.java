package com.crowdin.client.users.model;

import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.teams.model.Team;
import lombok.Data;

import java.util.List;

/**
 * Project permissions of a user or a team. Shared by
 * {@link com.crowdin.client.users.UsersApi#listUserProjectPermissions} and
 * {@link com.crowdin.client.teams.TeamsApi#listTeamProjectPermissions}
 */
@Data
public class ProjectPermissions {

    private Long id;
    private List<TranslatorRole> roles;
    private Project project;
    /**
     * Teams through which the user has access to the project. Returned only for user project permissions
     */
    private List<Team> teams;
}
