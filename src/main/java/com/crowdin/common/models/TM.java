package com.crowdin.common.models;

import com.crowdin.common.dto.ProjectShortDto;

import java.util.List;

public class TM {
    private long id;
    private long groupId;
    private String name;
    private List<Long> languageIds;
    private int segmentsCount;
    private long defaultProjectId;
    private List<ProjectShortDto> projects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
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

    public long getDefaultProjectId() {
        return defaultProjectId;
    }

    public void setDefaultProjectId(long defaultProjectId) {
        this.defaultProjectId = defaultProjectId;
    }

    public List<ProjectShortDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectShortDto> projects) {
        this.projects = projects;
    }
}
