package com.crowdin.common.request;

public class PropertyFileExportOptions implements ExportOptions {

    private Integer escapeQuotes;
    private String exportPattern;

    public Integer getEscapeQuotes() {
        return escapeQuotes;
    }

    public void setEscapeQuotes(Integer escapeQuotes) {
        this.escapeQuotes = escapeQuotes;
    }

    public String getExportPattern() {
        return exportPattern;
    }

    public void setExportPattern(String exportPattern) {
        this.exportPattern = exportPattern;
    }
}
