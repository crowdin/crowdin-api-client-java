package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Crowdin Enterprise "Vendor Task Create Form" request, "Create By String Ids Form" ({@code stringIds}).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskEnterpriseVendorStringsBasedRequest extends AddEnterpriseTaskStringsBasedRequest {

    private Long workflowStepId;
    private Boolean skipAssignedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private Date startedAt;
    private List<Long> labelIds;
    /**
     * Can only be used when {@code labelIds} is provided
     */
    private LabelMatchRule labelMatchRule;

}
