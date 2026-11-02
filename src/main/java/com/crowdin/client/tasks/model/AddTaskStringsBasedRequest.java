package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddTaskStringsBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> stringIds;
    private Status status;
    private Date dateFrom;
    private Date dateTo;
    /**
     * Start of the interval when translations were updated (proofread tasks only)
     */
    private Date translationsUpdatedDateFrom;
    /**
     * End of the interval when translations were updated (proofread tasks only)
     */
    private Date translationsUpdatedDateTo;
    /**
     * Generate cost estimate report for the task. Requires {@code reportSettingsTemplateId}
     */
    private Boolean generateCostEstimate;
    /**
     * Generate translation cost report for the task. Requires {@code reportSettingsTemplateId}
     */
    private Boolean generateTranslationCost;
    private Long reportSettingsTemplateId;
}
