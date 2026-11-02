package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Crowdin Enterprise "Task Create Form" request, "Create By String Ids Form" ({@code stringIds}).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskEnterpriseStringsBasedRequest extends AddEnterpriseTaskStringsBasedRequest {

    private Type type;
    private Long workflowStepId;
    private Status status;
    /**
     * @deprecated deprecated in the API, use {@code splitContent} instead
     */
    @Deprecated
    private Boolean splitFiles;
    private Boolean splitContent;
    private Boolean skipAssignedStrings;
    private List<AssigneeRequest> assignees;
    private List<AssigneeRequest> assignedTeams;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private Date startedAt;
    private Date dateFrom;
}
