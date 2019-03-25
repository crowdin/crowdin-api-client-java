package com.crowdin.common.request;

import java.util.List;

public class BuildTranslationPayload {

    private long branchId;
    private List<Long> targetLanguagesId;
    private boolean exportTranslatedOnly;
    private boolean exportApprovedOnly;
    private boolean force;

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public List<Long> getTargetLanguagesId() {
        return targetLanguagesId;
    }

    public void setTargetLanguagesId(List<Long> targetLanguagesId) {
        this.targetLanguagesId = targetLanguagesId;
    }

    public boolean isExportTranslatedOnly() {
        return exportTranslatedOnly;
    }

    public void setExportTranslatedOnly(boolean exportTranslatedOnly) {
        this.exportTranslatedOnly = exportTranslatedOnly;
    }

    public boolean isExportApprovedOnly() {
        return exportApprovedOnly;
    }

    public void setExportApprovedOnly(boolean exportApprovedOnly) {
        this.exportApprovedOnly = exportApprovedOnly;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
