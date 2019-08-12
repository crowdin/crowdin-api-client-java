package com.crowdin.common.request;

public class GeneralFileExportOptions implements ExportOptions {

    private String exportPattern;

    public String getExportPattern() {
        return exportPattern;
    }

    public void setExportPattern(String exportPattern) {
        this.exportPattern = exportPattern;
    }
}
