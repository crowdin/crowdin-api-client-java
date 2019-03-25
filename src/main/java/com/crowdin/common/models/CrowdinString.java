package com.crowdin.common.models;

public class CrowdinString {

    private long id;
    private long projectId;
    private long fileId;
    private String identifier;
    private String text;
    private int type;
    private String context;
    private int maxLength;
    private boolean isHidden;
    private int revision;
    private boolean hasPlurals;
    private String plurals;
    private boolean isIcu;
    private String createdAt;
    private String updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public boolean isHasPlurals() {
        return hasPlurals;
    }

    public void setHasPlurals(boolean hasPlurals) {
        this.hasPlurals = hasPlurals;
    }

    public String getPlurals() {
        return plurals;
    }

    public void setPlurals(String plurals) {
        this.plurals = plurals;
    }

    public boolean isIcu() {
        return isIcu;
    }

    public void setIcu(boolean icu) {
        isIcu = icu;
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
