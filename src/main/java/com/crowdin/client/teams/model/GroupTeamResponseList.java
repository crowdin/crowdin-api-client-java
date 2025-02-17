package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupTeamResponseList {

    private List<GroupTeamResponseObject> data;
    private Pagination pagination;

    public static ResponseList<GroupTeam> to(GroupTeamResponseList groupTeamResponseList) {
        return ResponseList.of(
                groupTeamResponseList.getData().stream()
                        .map(GroupTeamResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                groupTeamResponseList.getPagination()
        );
    }
}
