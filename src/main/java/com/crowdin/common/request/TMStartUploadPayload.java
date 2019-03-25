package com.crowdin.common.request;

public class TMStartUploadPayload {
    private boolean includeAssigned;
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private String format;

    public TMStartUploadPayload(boolean includeAssigned, String sourceLanguageCode, String targetLanguageCode, String format) {
        this.includeAssigned = includeAssigned;
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
        this.format = format;
    }

    public boolean isIncludeAssigned() {
        return includeAssigned;
    }

    public void setIncludeAssigned(boolean includeAssigned) {
        this.includeAssigned = includeAssigned;
    }

    public String getSourceLanguageCode() {
        return sourceLanguageCode;
    }

    public void setSourceLanguageCode(String sourceLanguageCode) {
        this.sourceLanguageCode = sourceLanguageCode;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
