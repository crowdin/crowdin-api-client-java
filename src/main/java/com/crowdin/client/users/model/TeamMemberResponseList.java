package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamMemberResponseList {

    private List<TeamMemberResponseObject> data;
    private Pagination pagination;

    public static ResponseList<TeamMember> to(TeamMemberResponseList teamMemberResponseList) {
        return ResponseList.of(
                teamMemberResponseList.getData().stream()
                        .map(TeamMemberResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                teamMemberResponseList.getPagination()
        );
    }
}
