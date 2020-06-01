package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Project {

    private Long id;
    private Long groupId;
    private Long userId;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private LanguageAccessPolicy languageAccessPolicy;
    private String name;
    private String identifier;
    private String description;
    private String logo;
    private String background;
    private boolean isExternal;
    private String externalType;
    private Long workflowId;
    private boolean hasCrowdsourcing;
    private boolean publicDownloads;
    private Date createdAt;
    private Date updatedAt;
    private Date lastActivity;

}
