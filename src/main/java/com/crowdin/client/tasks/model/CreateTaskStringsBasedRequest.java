package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskStringsBasedRequest extends AddTaskStringsBasedRequest {

    private Type type;
    private Boolean splitFiles;
    private Boolean splitContent;
    private Boolean skipAssignedStrings;
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private List<AssigneeRequest> assignees;
    private Date deadline;
    private Date startedAt;
}
