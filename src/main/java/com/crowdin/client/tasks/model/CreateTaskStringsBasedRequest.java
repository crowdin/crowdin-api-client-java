package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Crowdin "Task Create" request, "Create By String Ids Form" ({@code stringIds}).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskStringsBasedRequest extends AddTaskStringsBasedRequest {

    private Type type;
    /**
     * @deprecated deprecated in the API, use {@code splitContent} instead
     */
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
