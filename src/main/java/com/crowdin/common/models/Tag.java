package com.crowdin.common.models;

public class Tag {

    private long id;
    private long screenshotId;
    private long stringId;
    private Position position;
    private String createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScreenshotId() {
        return screenshotId;
    }

    public void setScreenshotId(long screenshotId) {
        this.screenshotId = screenshotId;
    }

    public long getStringId() {
        return stringId;
    }

    public void setStringId(long stringId) {
        this.stringId = stringId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
