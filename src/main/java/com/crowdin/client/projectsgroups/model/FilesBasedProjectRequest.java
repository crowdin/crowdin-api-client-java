package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FilesBasedProjectRequest extends AddProjectRequest {

    private Integer type;
    private Boolean normalizePlaceholder;
    private Boolean saveMetaInfoInSource;
    private String name;
    private String identifier;
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
    private Boolean useGlobalTm;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private String inContextPseudoLanguageId;
    private Boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private Map<String, Map<String, String>> languageMapping;

}
