package com.crowdin.common.request;

public class BranchPayload implements Request {

    private String name;
    private String title;
    private String exportPattern;
    private String priority;

    private BranchPayload() {
    }

    public BranchPayload(String name, String title, String exportPattern, String priority) {
        this.name = name;
        this.title = title;
        this.exportPattern = exportPattern;
        this.priority = priority;
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
