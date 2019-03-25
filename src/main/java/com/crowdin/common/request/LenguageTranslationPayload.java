package com.crowdin.common.request;

public class LenguageTranslationPayload {

    private String storageId;
    private long fileId;
    private boolean importDuplicates;
    private boolean importEqSuggestions;
    private boolean autoApproveImported;
    private String format;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public boolean isImportDuplicates() {
        return importDuplicates;
    }

    public void setImportDuplicates(boolean importDuplicates) {
        this.importDuplicates = importDuplicates;
    }

    public boolean isImportEqSuggestions() {
        return importEqSuggestions;
    }

    public void setImportEqSuggestions(boolean importEqSuggestions) {
        this.importEqSuggestions = importEqSuggestions;
    }

    public boolean isAutoApproveImported() {
        return autoApproveImported;
    }

    public void setAutoApproveImported(boolean autoApproveImported) {
        this.autoApproveImported = autoApproveImported;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
