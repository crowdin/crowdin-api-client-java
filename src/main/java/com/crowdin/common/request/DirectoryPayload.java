package com.crowdin.common.request;

public class DirectoryPayload implements Request {

    private Long branchId;
    private Long parentId;
    private String name;
    private String title;
    private String exportPattern;
    private String priority;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExportPattern() {
        return exportPattern;
    }

    public void setExportPattern(String exportPattern) {
        this.exportPattern = exportPattern;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
