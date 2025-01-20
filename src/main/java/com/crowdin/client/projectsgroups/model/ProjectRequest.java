package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectRequest extends AddProjectRequest {

    private String name;
    private String identifier;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String visibility;
    private String languageAccessPolicy;
    private String cname;
    private String description;
    private Integer tagDetection;
    private Boolean isMtAllowed;
    private Boolean taskBasedAccessControl;
    private Boolean autoSubstitution;
    private Boolean autoTranslateDialects;
    private Boolean publicDownloads;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean useGlobalTm;
    private Boolean showTmSuggestionsDialects;
    private Boolean skipUntranslatedStrings;
    private Boolean exportApprovedOnly;
    private Boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private Map<String, Map<String, String>> languageMapping;
    /**
     * @deprecated
     */
    private Boolean glossaryAccess;
    private String glossaryAccessOption;
    private Boolean normalizePlaceholder;
    private NotificationSettings notificationSettings;
    private TmPreTranslate tmPreTranslate;
    private MtPreTranslate mtPreTranslate;
    private AiPreTranslate aiPreTranslate;
    private Long assistActionAiPromptId;
    private Long editorSuggestionAiPromptId;
    private Long savingsReportSettingsTemplateId;
    private Integer defaultTmId;
    private Integer defaultGlossaryId;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private String inContextPseudoLanguageId;
    private Boolean saveMetaInfoInSource;
    private Integer type;
    private Boolean skipUntranslatedFiles;
    private TmContextType tmContextType;
}
