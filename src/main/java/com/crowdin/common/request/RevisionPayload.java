package com.crowdin.common.request;

import java.util.Map;

public class RevisionPayload {

    private Long storageId;
    private Map<String, Integer> scheme;
    private Boolean firstLineContainsHeader;
    private String updateOption;
    private Integer escapeQuotes;

    public RevisionPayload() {
    }

    public RevisionPayload(Long storageId, Map<String, Integer> scheme, Boolean firstLineContainsHeader, String updateOption, Integer escapeQuotes) {
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

    public Map<String, Integer> getScheme() {
        return scheme;
    }

    public void setScheme(Map<String, Integer> scheme) {
        this.scheme = scheme;
    }

    public Boolean getFirstLineContainsHeader() {
        return firstLineContainsHeader;
    }

    public void setFirstLineContainsHeader(Boolean firstLineContainsHeader) {
        this.firstLineContainsHeader = firstLineContainsHeader;
    }

    public String getUpdateOption() {
        return updateOption;
    }

    public void setUpdateOption(String updateOption) {
        this.updateOption = updateOption;
    }

    public Integer getEscapeQuotes() {
        return escapeQuotes;
    }

    public void setEscapeQuotes(Integer escapeQuotes) {
        this.escapeQuotes = escapeQuotes;
    }
}
