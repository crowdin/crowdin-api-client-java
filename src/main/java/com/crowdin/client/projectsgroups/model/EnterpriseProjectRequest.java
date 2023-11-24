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
    private Boolean isMtAllowed;
    private Boolean taskBasedAccessControl;
    private Boolean autoSubstitution;
    private Boolean autoTranslateDialects;
    private Boolean publicDownloads;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean useGlobalTm;
    private Boolean delayedWorkflowStart;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Integer exportWithMinApprovalsCount;
    private Boolean normalizePlaceholder;
    private Boolean saveMetaInfoInSource;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private Boolean inContextPseudoLanguageId;
    private Boolean qaCheckIsActive;
    private Integer qaApprovalsCount;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private List<Integer> customQaCheckIds;
    private TmContextType tmContextType;
    private Map<String, Map<String, String>> languageMapping;
    private Boolean glossaryAccess;
    private NotificationSettings notificationSettings;
    private TmPenalties tmPenalties;
}
