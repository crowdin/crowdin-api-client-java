package com.crowdin.common.request;

public class TranslationPayload {

    private Long storageId;
    private Long fileId;
    private boolean importDuplicates;
    private boolean importEqSuggestions;
    private boolean autoApproveImported;
    private String format;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
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
