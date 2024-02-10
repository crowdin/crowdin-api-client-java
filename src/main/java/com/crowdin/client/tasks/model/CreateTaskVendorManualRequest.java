package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorManualRequest extends AddTaskRequest {

    private TypeVendor type;
    private String vendor;
    private Boolean skipAssignedStrings;
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private List<AssigneeRequest> assignees;
    private Date deadline;
    private Date startedAt;
}
