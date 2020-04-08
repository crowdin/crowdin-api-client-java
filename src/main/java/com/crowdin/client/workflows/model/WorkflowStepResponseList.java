package com.crowdin.client.workflows.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WorkflowStepResponseList {

    private List<WorkflowStepResponseObject> data;
    private Pagination pagination;

    public static ResponseList<WorkflowStep> to(WorkflowStepResponseList workflowStepResponseList) {
        return ResponseList.of(
                workflowStepResponseList.getData().stream()
                        .map(WorkflowStepResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                workflowStepResponseList.getPagination()
        );
    }
}
