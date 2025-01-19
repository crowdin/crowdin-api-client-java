package com.crowdin.client.projectsgroups.model;

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
    private Boolean autoTranslateDialects;
    private Boolean publicDownloads;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean delayedWorkflowStart;
    private Boolean skipUntranslatedStrings;
    private Integer exportWithMinApprovalsCount;
    private Integer exportStringsThatPassedWorkflow;
    private Boolean normalizePlaceholder;
    private Boolean qaCheckIsActive;
    private Integer qaApprovalsCount;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private List<Integer> customQaCheckIds;
    private Map<String, Map<String, String>> languageMapping;
    /**
     * @deprecated
     */
    private Boolean glossaryAccess;
    private String glossaryAccessOption;
    private NotificationSettings notificationSettings;
    private Long savingsReportSettingsTemplateId;
    private Long assistActionAiPromptId;
    private Long editorSuggestionAiPromptId;
    private Long alignmentActionAiPromptId;
    private Integer defaultTmId;
    private Integer defaultGlossaryId;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private Boolean inContextPseudoLanguageId;
    private Boolean saveMetaInfoInSource;
    private Integer type;
    private Boolean skipUntranslatedFiles;
    private TmContextType tmContextType;
}
