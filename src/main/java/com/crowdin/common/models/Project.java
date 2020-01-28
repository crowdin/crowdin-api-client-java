package com.crowdin.common.models;

import java.util.List;

public class Project {
    private Long id;
    private Long groupId;
    private Long userId;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String languageAccessPolicy;
    private String name;
    private String cname;
    private String identifier;
    private String description;
    private String logo;
    private String background;
    private boolean isExternal;
    private String externalType;
    private int advancedWorkflowId;
    private boolean hasCrowdsourcing;
    private String createdAt;
    private String updatedAt;

    public int getAdvancedWorkflowId() {
        return advancedWorkflowId;
    }

    public void setAdvancedWorkflowId(int advancedWorkflowId) {
        this.advancedWorkflowId = advancedWorkflowId;
    }


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSourceLanguageId() {
        return sourceLanguageId;
    }

    public void setSourceLanguageId(String sourceLanguageId) {
        this.sourceLanguageId = sourceLanguageId;
    }

    public List<String> getTargetLanguageIds() {
        return targetLanguageIds;
    }

    public void setTargetLanguageIds(List<String> targetLanguageIds) {
        this.targetLanguageIds = targetLanguageIds;
    }

    public String getLanguageAccessPolicy() {
        return languageAccessPolicy;
    }

    public void setLanguageAccessPolicy(String languageAccessPolicy) {
        this.languageAccessPolicy = languageAccessPolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public String getExternalType() {
        return externalType;
    }

    public void setExternalType(String externalType) {
        this.externalType = externalType;
    }

    public boolean isHasCrowdsourcing() {
        return hasCrowdsourcing;
    }

    public void setHasCrowdsourcing(boolean hasCrowdsourcing) {
        this.hasCrowdsourcing = hasCrowdsourcing;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
