package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ProjectMembersResponse {

    private List<ProjectMemberResponseObject> skipped;
    private List<ProjectMemberResponseObject> added;
    private Pagination pagination;
}
