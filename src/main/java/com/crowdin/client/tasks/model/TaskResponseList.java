package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TaskResponseList {

    private List<TaskResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Task> to(TaskResponseList taskResponseList) {
        return ResponseList.of(
                taskResponseList.getData().stream()
                        .map(TaskResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                taskResponseList.getPagination()
        );
    }
}
