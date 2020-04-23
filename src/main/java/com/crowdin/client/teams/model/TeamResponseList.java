package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamResponseList {

    private List<TeamResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Team> to(TeamResponseList teamResponseList) {
        return ResponseList.of(
                teamResponseList.getData().stream()
                        .map(TeamResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                teamResponseList.getPagination()
        );
    }
}
