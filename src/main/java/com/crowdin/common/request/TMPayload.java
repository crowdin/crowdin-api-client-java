package com.crowdin.common.request;

public class TMPayload {
    private Long storageId;
    private boolean firstLineContainsHeader;
    private String scheme;

    public TMPayload(Long storageId, boolean firstLineContainsHeader, String scheme) {
        this.storageId = storageId;
        this.firstLineContainsHeader = firstLineContainsHeader;
        this.scheme = scheme;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
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