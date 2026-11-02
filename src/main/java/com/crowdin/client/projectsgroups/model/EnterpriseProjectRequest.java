package com.crowdin.client.projectsgroups.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnterpriseProjectRequest extends AddProjectRequest {

    private String name;
    private String sourceLanguageId;
    private Long templateId;
    private List<WorkflowTemplateStepConfig> steps;
    private Long groupId;
    private List<String> targetLanguageIds;
    private Long vendorId;
    private Long mtEngineId;
    private String description;
    private Integer translateDuplicates;
    private Integer tagsDetection;
    private Boolean isMtAllowed;
    private Boolean taskBasedAccessControl;
    private List<Long> taskReviewerIds;
    private Boolean autoSubstitution;
    private Boolean showTmSuggestionsDialects;
    private Boolean tmApprovedSuggestionsOnly;
    private Boolean autoTranslateDialects;
    private Boolean publicDownloads;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean delayedWorkflowStart;
    private Boolean skipUntranslatedStrings;
    private Integer exportWithMinApprovalsCount;
    /**
     * @deprecated the API expects a boolean, use {@link #exportStringsThatPassedWorkflowEnabled} instead.
     * The legacy setter maps {@code 0} to {@code false} and any other value to {@code true}
     */
    @Deprecated
    @JsonIgnore
    private Integer exportStringsThatPassedWorkflow;
    @JsonProperty("exportStringsThatPassedWorkflow")
    private Boolean exportStringsThatPassedWorkflowEnabled;
    private Boolean normalizePlaceholder;
    private Boolean qaCheckIsActive;
    private Integer qaApprovalsCount;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private List<Integer> customQaCheckIds;
    private List<Long> externalQaCheckIds;
    /**
     * External QA check IDs mapped to whether a failure of the check can be ignored
     */
    private Map<String, Boolean> externalQaChecksIgnorable;
    private Map<String, Map<String, String>> languageMapping;
    /**
     * @deprecated deprecated in the API, use {@link #glossaryAccessOption} instead
     */
    @Deprecated
    private Boolean glossaryAccess;
    private String glossaryAccessOption;
    private NotificationSettings notificationSettings;
    private Long savingsReportSettingsTemplateId;
    /**
     * @deprecated no longer part of the documented API
     */
    @Deprecated
    private Long assistActionAiPromptId;
    private Long editorSuggestionAiPromptId;
    private Long qaCheckActionAiPromptId;
    private Long contextReviewAiPromptId;
    private List<Long> assignedStyleGuides;
    private Long alignmentActionAiPromptId;
    private Integer defaultTmId;
    private Integer defaultGlossaryId;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    /**
     * @deprecated the API expects a language identifier string, use {@link #inContextPseudoLanguageIdValue} instead.
     * This field is not sent to the API
     */
    @Deprecated
    @JsonIgnore
    private Boolean inContextPseudoLanguageId;
    @JsonProperty("inContextPseudoLanguageId")
    private String inContextPseudoLanguageIdValue;
    private Boolean saveMetaInfoInSource;
    private Integer type;
    private Boolean skipUntranslatedFiles;
    private TmContextType tmContextType;
    /**
     * Keys are field slugs
     */
    private Map<String, Object> fields;

    /**
     * @return 1 if enabled, 0 if disabled, null if not set
     * @deprecated use {@link #exportStringsThatPassedWorkflowEnabled} instead
     */
    @Deprecated
    public Integer getExportStringsThatPassedWorkflow() {
        if (this.exportStringsThatPassedWorkflowEnabled == null) {
            return null;
        }
        return this.exportStringsThatPassedWorkflowEnabled ? 1 : 0;
    }

    /**
     * @param exportStringsThatPassedWorkflow 0 to disable, any other value to enable
     * @deprecated use {@link #exportStringsThatPassedWorkflowEnabled} instead
     */
    @Deprecated
    public void setExportStringsThatPassedWorkflow(Integer exportStringsThatPassedWorkflow) {
        this.exportStringsThatPassedWorkflowEnabled = exportStringsThatPassedWorkflow == null
                ? null
                : exportStringsThatPassedWorkflow != 0;
    }
}
