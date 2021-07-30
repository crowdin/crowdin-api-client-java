package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectMemberResponseList {

    private List<ProjectMemberResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ProjectMember> to(ProjectMemberResponseList projectMemberResponseList) {
        return ResponseList.of(
            projectMemberResponseList.getData().stream()
                .map(ProjectMemberResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            projectMemberResponseList.getPagination()
        );
    }
}
