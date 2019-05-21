package com.crowdin.common.models;

public class Translation {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long languageId;
    private String status;
    private Progress progress;

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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public class Progress {
        private int percent;
        private String currentLanguageName;
        private String currentFileName;

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getCurrentLanguageName() {
            return currentLanguageName;
        }

        public void setCurrentLanguageName(String currentLanguageName) {
            this.currentLanguageName = currentLanguageName;
        }

        public String getCurrentFileName() {
            return currentFileName;
        }

        public void setCurrentFileName(String currentFileName) {
            this.currentFileName = currentFileName;
        }
    }
}
