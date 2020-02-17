package com.crowdin.common.models;

import com.crowdin.common.request.GeneralFileExportOptions;
import com.crowdin.common.request.SpreadsheetFileImportOptions;

public class FileEntity {
    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;

    private String name;
    private String title;
    private String type;

    private int revision;
    private String status;
    private String priority;

    private SpreadsheetFileImportOptions importOptions;
    private GeneralFileExportOptions exportOptions;

    private String createdAt;
    private String updatedAt;

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

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public SpreadsheetFileImportOptions getImportOptions() {
        return importOptions;
    }

    public void setImportOptions(SpreadsheetFileImportOptions importOptions) {
        this.importOptions = importOptions;
    }

    public GeneralFileExportOptions getExportOptions() {
        return exportOptions;
    }

    public void setExportOptions(GeneralFileExportOptions exportOptions) {
        this.exportOptions = exportOptions;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
