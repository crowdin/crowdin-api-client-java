package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Crowdin "Task Create" request, "Create By Source Ids Form" ({@code fileIds} / {@code directoryIds} / {@code branchIds}).
 * <p>
 * In string-based projects use {@code branchIds} (file and directory identifiers are not supported there).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskRequest extends AddTaskFilesBasedRequest {

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
