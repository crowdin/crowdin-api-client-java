package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectTeamResourceResponseList {

    private List<ProjectTeamResourceResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ProjectTeamResource> to(ProjectTeamResourceResponseList projectTeamResourceResponseList) {
        return ResponseList.of(
                projectTeamResourceResponseList.getData().stream()
                        .map(ProjectTeamResourceResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                projectTeamResourceResponseList.getPagination()
        );
    }
}
