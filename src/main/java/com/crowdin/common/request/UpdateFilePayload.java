package com.crowdin.common.request;

public class UpdateFilePayload implements Request {

    private Long storageId;
    private String updateOption;

    private ImportOptions importOptions;
    private ExportOptions exportOptions;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getUpdateOption() {
        return updateOption;
    }

    public void setUpdateOption(String updateOption) {
        this.updateOption = updateOption;
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
