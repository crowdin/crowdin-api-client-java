package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AddTeamMembersResponseInternal {

    private List<TeamMemberResponseObject> skipped;
    private List<TeamMemberResponseObject> added;
    private Pagination pagination;

    public static AddTeamMembersResponse to(AddTeamMembersResponseInternal addTeamMembersResponseInternal) {
        AddTeamMembersResponse response = new AddTeamMembersResponse();
        response.setPagination(addTeamMembersResponseInternal.getPagination());
        response.setAdded(addTeamMembersResponseInternal.getAdded().stream().map(TeamMemberResponseObject::getData).map(ResponseObject::of).collect(Collectors.toList()));
        response.setSkipped(addTeamMembersResponseInternal.getSkipped().stream().map(TeamMemberResponseObject::getData).map(ResponseObject::of).collect(Collectors.toList()));
        return response;
    }
}
