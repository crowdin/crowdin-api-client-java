package com.crowdin.common.models;

import java.util.List;

public class Project {
    private Long id;
    private Long organizationId;
    private Long groupId;
    private Long userId;
    private int status;
    private String sourceLanguageId;
    private List<String> targetLanguageIds;
    private String joinPolicy;
    private String languageAccessPolicy;
    private int type;
    private String name;
    private String cname;
    private String identifier;
    private String description;
    private String visibility;
    private String logo;
    private String background;
    private boolean isExternal;
    private String externalType;
    private boolean hasWorkflow;
    private boolean hasCrowdsourcing;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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

    public boolean isHasWorkflow() {
        return hasWorkflow;
    }

    public void setHasWorkflow(boolean hasWorkflow) {
        this.hasWorkflow = hasWorkflow;
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
