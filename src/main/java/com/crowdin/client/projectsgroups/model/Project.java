package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Project {

    private Integer id;
    private Integer groupId;
    private Integer userId;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String name;
    private String identifier;
    private String description;
    private String logo;
    private String background;
    private boolean isExternal;
    private String externalType;
    private Integer workflowId;
    private boolean hasCrowdsourcing;
    private Date createdAt;
    private Date updatedAt;

}
