package com.crowdin.common.models;

public class Revision {
    private long id;
    private long projectId;
    private int revision;
    private int revertTo;
    private int translationChunks;
    //todo test it
    private String info;
    private String date;

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
