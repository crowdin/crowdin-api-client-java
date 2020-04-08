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
}
