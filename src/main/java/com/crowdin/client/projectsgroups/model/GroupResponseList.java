package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupResponseList {

    private List<GroupResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Group> to(GroupResponseList groupResponseList) {
        return ResponseList.of(
                groupResponseList.getData().stream()
                        .map(GroupResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                groupResponseList.getPagination()
        );
    }
}
