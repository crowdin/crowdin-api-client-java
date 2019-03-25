package com.crowdin.common.models;

public class Translation {

    private long id;
    private long projectId;
    private long branchId;
    private long languageId;
    private String status;
    private Progress progress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
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
