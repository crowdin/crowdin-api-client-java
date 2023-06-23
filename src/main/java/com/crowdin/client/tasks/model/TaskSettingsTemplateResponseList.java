package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TaskSettingsTemplateResponseList {
    private List<TaskSettingsTemplateResponseObject> data;
    private Pagination pagination;

    public static ResponseList<TaskSettingsTemplate> to(TaskSettingsTemplateResponseList responseList) {
        return ResponseList.of(
                responseList.getData().stream()
                        .map(TaskSettingsTemplateResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
