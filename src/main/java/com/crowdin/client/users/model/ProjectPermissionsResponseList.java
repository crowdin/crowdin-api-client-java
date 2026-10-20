package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectPermissionsResponseList {

    private List<ProjectPermissionsResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ProjectPermissions> to(ProjectPermissionsResponseList projectPermissionsResponseList) {
        return ResponseList.of(
                projectPermissionsResponseList.getData().stream()
                        .map(ProjectPermissionsResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                projectPermissionsResponseList.getPagination()
        );
    }
}
