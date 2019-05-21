package com.crowdin.common.models;

import com.crowdin.common.dto.ProjectShortDto;

import java.util.List;

public class TM {
    private Long id;
    private Long groupId;
    private String name;
    private List<Long> languageIds;
    private int segmentsCount;
    private Long defaultProjectId;
    private List<ProjectShortDto> projects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getLanguageIds() {
        return languageIds;
    }

    public void setLanguageIds(List<Long> languageIds) {
        this.languageIds = languageIds;
    }

    public int getSegmentsCount() {
        return segmentsCount;
    }

    public void setSegmentsCount(int segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public Long getDefaultProjectId() {
        return defaultProjectId;
    }

    public void setDefaultProjectId(Long defaultProjectId) {
        this.defaultProjectId = defaultProjectId;
    }

    public List<ProjectShortDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectShortDto> projects) {
        this.projects = projects;
    }
}
