package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;

@Data
public class AddTeamMembersResponse {

    private List<ResponseObject<TeamMember>> skipped;
    private List<ResponseObject<TeamMember>> added;
    private Pagination pagination;
}
