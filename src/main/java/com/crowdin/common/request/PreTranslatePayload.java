package com.crowdin.common.request;

import java.util.List;

public class PreTranslatePayload {

    private List<String> languageIds;
    private List<Long> fileIds;
    private String method;
    private String engine;
    private boolean approveTranslated;
    private int autoApproveOption;
    private boolean importDuplicates;
    private boolean applyUntranslatedStringsOnly;
    private boolean perfectMatch;

    public PreTranslatePayload(List<String> languageIds,
                               List<Long> fileIds,
                               String method,
                               String engine,
                               boolean approveTranslated,
                               int autoApproveOption,
                               boolean importDuplicates,
                               boolean applyUntranslatedStringsOnly,
                               boolean perfectMatch) {
        this.languageIds = languageIds;
        this.fileIds = fileIds;
        this.method = method;
        this.engine = engine;
        this.approveTranslated = approveTranslated;
        this.autoApproveOption = autoApproveOption;
        this.importDuplicates = importDuplicates;
        this.applyUntranslatedStringsOnly = applyUntranslatedStringsOnly;
        this.perfectMatch = perfectMatch;
    }

    public List<String> getLanguageIds() {
        return languageIds;
    }

    public void setLanguageIds(List<String> languageIds) {
        this.languageIds = languageIds;
    }

    public List<Long> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public boolean isApproveTranslated() {
        return approveTranslated;
    }

    public void setApproveTranslated(boolean approveTranslated) {
        this.approveTranslated = approveTranslated;
    }

    public int getAutoApproveOption() {
        return autoApproveOption;
    }

    public void setAutoApproveOption(int autoApproveOption) {
        this.autoApproveOption = autoApproveOption;
    }

    public boolean isImportDuplicates() {
        return importDuplicates;
    }

    public void setImportDuplicates(boolean importDuplicates) {
        this.importDuplicates = importDuplicates;
    }

    public boolean isApplyUntranslatedStringsOnly() {
        return applyUntranslatedStringsOnly;
    }

    public void setApplyUntranslatedStringsOnly(boolean applyUntranslatedStringsOnly) {
        this.applyUntranslatedStringsOnly = applyUntranslatedStringsOnly;
    }

    public boolean isPerfectMatch() {
        return perfectMatch;
    }

    public void setPerfectMatch(boolean perfectMatch) {
        this.perfectMatch = perfectMatch;
    }
}
