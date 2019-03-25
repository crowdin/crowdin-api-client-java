package com.crowdin.common.models;

import java.util.List;

public class Issue {
    private long id;
    private long projectId;
    private long creatorId;
    private int type;
    private int status;
    private String title;
    private List<Assignee> assignees;
    private List<Long> fileIds;
    private Progress progress;
    private long sourceLanguageId;
    private long targetLanguageId;
    private String description;
    private String hash;
    private String translationUrl;
    private int wordsCount;
    private int filesCount;
    private int commentsCount;
    private String deadline;
    private String timeRange;
    private String workflowStepTitle;
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

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public List<Long> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public long getSourceLanguageId() {
        return sourceLanguageId;
    }

    public void setSourceLanguageId(long sourceLanguageId) {
        this.sourceLanguageId = sourceLanguageId;
    }

    public long getTargetLanguageId() {
        return targetLanguageId;
    }

    public void setTargetLanguageId(long targetLanguageId) {
        this.targetLanguageId = targetLanguageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTranslationUrl() {
        return translationUrl;
    }

    public void setTranslationUrl(String translationUrl) {
        this.translationUrl = translationUrl;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getWorkflowStepTitle() {
        return workflowStepTitle;
    }

    public void setWorkflowStepTitle(String workflowStepTitle) {
        this.workflowStepTitle = workflowStepTitle;
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

    public class Progress {
        private int total;
        private int done;
        private int percent;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getDone() {
            return done;
        }

        public void setDone(int done) {
            this.done = done;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
