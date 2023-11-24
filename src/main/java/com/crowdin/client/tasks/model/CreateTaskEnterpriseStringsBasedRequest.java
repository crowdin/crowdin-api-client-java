package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskEnterpriseStringsBasedRequest extends AddTaskStringsBasedRequest {

    private Type type;
    private Long workflowStepId;
    private Boolean splitContent;
    private Boolean skipAssignedStrings;
    private List<AssigneeRequest> assignees;
    private List<AssigneeRequest> assignedTeams;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private Date startedAt;

}
