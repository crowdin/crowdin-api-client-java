package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.languages.model.Language;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Project {

    private Long id;
    private Type type;
    private Long userId;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String languageAccessPolicy;
    private String name;
    private String identifier;
    private String description;
    private String visibility;
    private String logo;
    private boolean publicDownloads;
    private Date createdAt;
    private Date updatedAt;
    private Date lastActivity;
    private Language sourceLanguage;
    private List<Language> targetLanguages;
    private String webUrl;
    private Long savingsReportSettingsTemplateId;
    //community
    private String cname;
    //enterprise
    private Long groupId;
    /**
     * @deprecated deprecated in the API
     */
    @Deprecated
    private String background;
    private boolean isExternal;
    private String externalType;
    private Long externalProjectId;
    private Long externalOrganizationId;
    private Long workflowId;
    private boolean hasCrowdsourcing;
    private String publicUrl;
    /**
     * Enterprise only. Keys are field slugs
     */
    private Map<String, Object> fields;
}
