package com.crowdin.common.models;

import java.util.List;

public class Translation {

    private Long id;
    private Long projectId;
    private String status;
    private Long progress;
    private Attributes attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public class Attributes {
        private Long branchId;
        private List<String> targetLanguageIds;
        private Boolean exportTranslatedOnly;
        private Boolean exportApprovedOnly;

        public Long getBranchId() {
            return branchId;
        }

        public void setBranchId(Long branchId) {
            this.branchId = branchId;
        }

        public List<String> getTargetLanguageIds() {
            return targetLanguageIds;
        }

        public void setTargetLanguageIds(List<String> targetLanguageIds) {
            this.targetLanguageIds = targetLanguageIds;
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
    }
}
