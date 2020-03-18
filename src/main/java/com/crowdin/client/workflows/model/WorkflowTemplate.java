package com.crowdin.client.workflows.model;

import lombok.Data;

@Data
public class WorkflowTemplate {

    private Integer id;
    private String title;
    private String description;
    private Integer groupId;
    private boolean isDefault;
}
