package com.crowdin.common.request;

import com.crowdin.common.models.Assignee;

import java.util.List;

public class IssuePayload {

    private String workflowStepId;
    private int status;
    private String title;
    private String description;
    private long languageId;
    private List<Long> fileIds;
    private boolean splitFiles;
    //todo test
    private List<Assignee> assignees;
    private String deadline;
    private String dateFrom;
    private String dateTo;

    public String getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(String workflowStepId) {
        this.workflowStepId = workflowStepId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public List<Long> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }

    public boolean isSplitFiles() {
        return splitFiles;
    }

    public void setSplitFiles(boolean splitFiles) {
        this.splitFiles = splitFiles;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
