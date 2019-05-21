package com.crowdin.common.request;

public class RevisionPayload {

    private Long storageId;
    private String scheme;
    private boolean firstLineContainsHeader;
    private int updateOption;
    private int escapeQuotes;

    public RevisionPayload(Long storageId, String scheme, boolean firstLineContainsHeader, int updateOption, int escapeQuotes) {
        this.storageId = storageId;
        this.scheme = scheme;
        this.firstLineContainsHeader = firstLineContainsHeader;
        this.updateOption = updateOption;
        this.escapeQuotes = escapeQuotes;
    }

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

    public int getUpdateOption() {
        return updateOption;
    }

    public void setUpdateOption(int updateOption) {
        this.updateOption = updateOption;
    }

    public int getEscapeQuotes() {
        return escapeQuotes;
    }

    public void setEscapeQuotes(int escapeQuotes) {
        this.escapeQuotes = escapeQuotes;
    }
}
