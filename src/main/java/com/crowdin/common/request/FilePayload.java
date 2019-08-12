package com.crowdin.common.request;

public class FilePayload implements Request {
    private Long branchId;
    private Long directoryId;
    private Long storageId;

    private String name;
    private String title;

    private String type;

    private ImportOptions importOptions;
    private ExportOptions exportOptions;

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

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
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

    public ImportOptions getImportOptions() {
        return importOptions;
    }

    public void setImportOptions(ImportOptions importOptions) {
        this.importOptions = importOptions;
    }

    public ExportOptions getExportOptions() {
        return exportOptions;
    }

    public void setExportOptions(ExportOptions exportOptions) {
        this.exportOptions = exportOptions;
    }
}
