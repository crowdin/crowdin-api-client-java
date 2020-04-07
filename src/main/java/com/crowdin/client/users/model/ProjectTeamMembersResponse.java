package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ProjectTeamMembersResponse {

    private List<ProjectTeamMemberResponseObject> skipped;
    private List<ProjectTeamMemberResponseObject> added;
    private Pagination pagination;
}
