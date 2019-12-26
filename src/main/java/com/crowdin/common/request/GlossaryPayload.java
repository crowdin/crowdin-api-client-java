package com.crowdin.common.request;

public class GlossaryPayload {
    private Long storageId;
    private String scheme;
    private boolean firstLineContainsHeader;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public boolean isFirstLineContainsHeader() {
        return firstLineContainsHeader;
    }

    public void setFirstLineContainsHeader(boolean firstLineContainsHeader) {
        this.firstLineContainsHeader = firstLineContainsHeader;
    }
}