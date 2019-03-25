package com.crowdin.common.request;

import java.util.List;

public class ProjectPayload {

    private String name;
    private String identifier;
    private int type;
    private int sourceLanguageId;
    private List<Long> targetLanguageIds;
    private String joinPolicy;
    private String languageAccessPolicy;
    private String cname;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSourceLanguageId() {
        return sourceLanguageId;
    }

    public void setSourceLanguageId(int sourceLanguageId) {
        this.sourceLanguageId = sourceLanguageId;
    }

    public List<Long> getTargetLanguageIds() {
        return targetLanguageIds;
    }

    public void setTargetLanguageIds(List<Long> targetLanguageIds) {
        this.targetLanguageIds = targetLanguageIds;
    }

    public String getJoinPolicy() {
        return joinPolicy;
    }

    public void setJoinPolicy(String joinPolicy) {
        this.joinPolicy = joinPolicy;
    }

    public String getLanguageAccessPolicy() {
        return languageAccessPolicy;
    }

    public void setLanguageAccessPolicy(String languageAccessPolicy) {
        this.languageAccessPolicy = languageAccessPolicy;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
