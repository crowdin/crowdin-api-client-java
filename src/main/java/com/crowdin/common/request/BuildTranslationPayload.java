package com.crowdin.common.request;

import java.util.List;

public class BuildTranslationPayload {

    private Long branchId;
    private List<Long> targetLanguagesId;
    private Boolean exportTranslatedOnly;
    private Boolean exportApprovedOnly;
    private Boolean force;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public List<Long> getTargetLanguagesId() {
        return targetLanguagesId;
    }

    public void setTargetLanguagesId(List<Long> targetLanguagesId) {
        this.targetLanguagesId = targetLanguagesId;
    }

    public Boolean getExportTranslatedOnly() {
        return exportTranslatedOnly;
    }

    public void setExportTranslatedOnly(Boolean exportTranslatedOnly) {
        this.exportTranslatedOnly = exportTranslatedOnly;
    }

    public Boolean getExportApprovedOnly() {
        return exportApprovedOnly;
    }

    public void setExportApprovedOnly(Boolean exportApprovedOnly) {
        this.exportApprovedOnly = exportApprovedOnly;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }
}
