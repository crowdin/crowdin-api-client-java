package com.crowdin.client.users.model;

import com.crowdin.client.teams.model.GroupTeam;
import lombok.Data;

import java.util.List;

@Data
public class GroupManager {
    private Long id;
    private User user;
    private List<GroupTeam> teams;
}
