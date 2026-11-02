package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Crowdin Enterprise "Vendor Task Create Form" request, "Create By Source Ids Form" ({@code fileIds} / {@code directoryIds} / {@code branchIds}).
 * <p>
 * In string-based projects use {@code branchIds} (file and directory identifiers are not supported there).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskEnterpriseVendorRequest extends AddEnterpriseTaskFilesBasedRequest {

    private Long workflowStepId;
    private Boolean skipAssignedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private Date startedAt;

}
