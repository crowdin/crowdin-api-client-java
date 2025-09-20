package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TaskCommentResponseList {

    private List<TaskCommentResponseObject> data;
    private Pagination pagination;

    public static ResponseList<TaskComment> to(TaskCommentResponseList taskCommentResponseList) {
        return ResponseList.of(
                taskCommentResponseList.getData().stream()
                        .map(TaskCommentResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                taskCommentResponseList.getPagination()
        );
    }
}
