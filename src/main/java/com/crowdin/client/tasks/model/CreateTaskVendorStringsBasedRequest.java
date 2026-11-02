package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Crowdin "Vendor Task Create" request, "Create By String Ids Form" ({@code stringIds}).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorStringsBasedRequest extends AddTaskStringsBasedRequest {

    private TypeVendor type;
    private String vendor;
    private Boolean skipAssignedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private List<Long> labelIds;
    /**
     * Can only be used when {@code labelIds} is provided
     */
    private LabelMatchRule labelMatchRule;
    private List<Long> excludeLabelIds;
    /**
     * Can only be used when {@code excludeLabelIds} is provided
     */
    private LabelMatchRule excludeLabelMatchRule;
}
