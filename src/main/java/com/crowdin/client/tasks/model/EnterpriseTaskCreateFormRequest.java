package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnterpriseTaskCreateFormRequest extends AddTaskRequest {

    private Long workflowStepId;
    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Status status;
    private String description;
    private Boolean splitFiles;
    private Boolean skipAssignedStrings;
    private List<AssigneeRequest> assignees;
    private Date deadline;
    private List<Long> labelIds;
    private Date dateFrom;
    private Date dateTo;

}
