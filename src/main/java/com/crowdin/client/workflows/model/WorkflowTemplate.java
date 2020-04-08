package com.crowdin.client.workflows.model;

import lombok.Data;

@Data
public class WorkflowTemplate {

    private Long id;
    private String title;
    private String description;
    private Long groupId;
    private boolean isDefault;
}
