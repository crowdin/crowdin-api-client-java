package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class AddProjectRequest {

    private String name;
    private String sourceLanguageId;
    private Long templateId;
    private Long groupId;
    private List<String> targetLanguageIds;
    private Long vendorId;
    private Long mtEngineId;
    private String description;
    private Boolean delayedWorkflowStart;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Integer exportWithMinApprovalsCount;
    private Integer type;
    private String cname;
    private LanguageAccessPolicy languageAccessPolicy;
    private String identifier;
    private Visibility visibility;
}
