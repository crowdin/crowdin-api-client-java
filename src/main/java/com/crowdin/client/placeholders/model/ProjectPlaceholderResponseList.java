package com.crowdin.client.placeholders.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectPlaceholderResponseList {

    private List<ProjectPlaceholderResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ProjectPlaceholder> to(ProjectPlaceholderResponseList projectPlaceholderResponseList) {
        return ResponseList.of(
                projectPlaceholderResponseList.getData().stream()
                        .map(ProjectPlaceholderResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                projectPlaceholderResponseList.getPagination()
        );
    }
}
