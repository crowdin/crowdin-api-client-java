package com.crowdin.client.teams.model;

import lombok.Data;

import java.util.List;

@Data
public class AddTeamMembersRequest {

    private List<Long> userIds;
}
