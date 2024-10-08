package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskRequest extends AddTaskFilesBasedRequest {

    private Type type;
    @Deprecated
    private Boolean splitFiles;
    private Boolean splitContent;
    private Boolean skipAssignedStrings;
    @Deprecated
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private List<AssigneeRequest> assignees;
    private Date deadline;
    private Date startedAt;
}
