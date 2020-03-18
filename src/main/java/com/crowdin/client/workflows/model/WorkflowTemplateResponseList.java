package com.crowdin.client.workflows.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WorkflowTemplateResponseList {

    private List<WorkflowTemplateResponseObject> data;
    private Pagination pagination;

    public static ResponseList<WorkflowTemplate> to(WorkflowTemplateResponseList workflowTemplateResponseList) {
        return ResponseList.of(
                workflowTemplateResponseList.getData().stream()
                        .map(WorkflowTemplateResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                workflowTemplateResponseList.getPagination()
        );
    }
}
