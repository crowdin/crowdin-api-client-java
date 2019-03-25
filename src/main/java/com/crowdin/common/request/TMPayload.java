package com.crowdin.common.request;

public class TMPayload {
    private long storageId;
    private boolean firstLineContainsHeader;
    private String scheme;

    public TMPayload(long storageId, boolean firstLineContainsHeader, String scheme) {
        this.storageId = storageId;
        this.firstLineContainsHeader = firstLineContainsHeader;
        this.scheme = scheme;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    public boolean isFirstLineContainsHeader() {
        return firstLineContainsHeader;
    }

    public void setFirstLineContainsHeader(boolean firstLineContainsHeader) {
        this.firstLineContainsHeader = firstLineContainsHeader;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}