package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddEnterpriseTaskStringsBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> stringIds;
    private Date dateTo;
    private Map<String, Object> fields;
    /**
     * Can only be used when {@code skipAssignedStrings} is {@code true}
     */
    private SkipAssignedStringsScope skipAssignedStringsScope;
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
