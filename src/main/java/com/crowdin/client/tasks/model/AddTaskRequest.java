package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddTaskRequest {

    private Long workflowStepId;
    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Status status;
    private String description;
    private Boolean splitFiles;
    private Boolean skipAssignedStrings;
    private Boolean skipUntranslatedStrings;
    private List<Assignee> assignees;
    private Date deadline;
    private Date dateFrom;
    private Date dateTo;
}
