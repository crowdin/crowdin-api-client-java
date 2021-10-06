package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.languages.model.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectSettings extends Project {

    private Integer translateDuplicates;
    private Boolean glossaryAccess;
    private Boolean isMtAllowed;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean autoSubstitution;
    private Boolean skipTranslatedOnly;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Integer exportWithMinApprovalsCount;
    private boolean autoTranslateDialects;
    private boolean useGlobalTm;
    private Boolean normalizePlaceholder;
    private Boolean saveMetaInfoInSource;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private String inContextPseudoLanguageId;
    private Language inContextPseudoLanguage;
    private Boolean isSuspended;
    private Boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private List<Long> customQaCheckIds;
    private Map<String, Map<String, String>> languageMapping;
    private Boolean delayedWorkflowStart;
    private NotificationSettings notificationSettings;

}
