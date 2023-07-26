package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class StringsBasedProjectRequest extends AddProjectRequest {

    private String name;
    private String identifier;
    private Integer type;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String visibility;
    private String languageAccessPolicy;
    private String cname;
    private String description;
    private Integer translateDuplicates;
    private Boolean isMtAllowed;
    private Boolean autoSubstitution;
    private Boolean autoTranslateDialects;
    private Boolean publicDownloads;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean useGlobalTm;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private String inContextPseudoLanguageId;
    private Boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private Map<String, Map<String, String>> languageMapping;
    private Boolean glossaryAccess;
    private NotificationSettings notificationSettings;
    private TmPenalties tmPenalties;
}
