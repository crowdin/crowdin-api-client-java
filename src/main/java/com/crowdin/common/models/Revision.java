package com.crowdin.common.models;

public class Revision {
    private Long id;
    private Long projectId;
    private int revision;
    private int revertTo;
    private int translationChunks;
    //todo test it
    private String info;
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public int getRevertTo() {
        return revertTo;
    }

    public void setRevertTo(int revertTo) {
        this.revertTo = revertTo;
    }

    public int getTranslationChunks() {
        return translationChunks;
    }

    public void setTranslationChunks(int translationChunks) {
        this.translationChunks = translationChunks;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
