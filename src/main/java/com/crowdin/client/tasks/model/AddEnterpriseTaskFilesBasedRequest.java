package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddEnterpriseTaskFilesBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> fileIds;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private Date dateTo;
    private Map<String, Object> fields;
    /**
     * File-based projects only. One of {@code fileIds}, {@code directoryIds} or {@code branchIds} is required
     */
    private List<Long> directoryIds;
    /**
     * One of {@code fileIds}, {@code directoryIds} or {@code branchIds} is required.
     * In string-based projects only {@code branchIds} is supported
     */
    private List<Long> branchIds;
    /**
     * Can only be used when {@code labelIds} is provided
     */
    private LabelMatchRule labelMatchRule;
    /**
     * Can only be used when {@code excludeLabelIds} is provided
     */
    private LabelMatchRule excludeLabelMatchRule;
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