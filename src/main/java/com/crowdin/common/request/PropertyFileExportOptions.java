package com.crowdin.common.request;

public class PropertyFileExportOptions implements ExportOptions {

    private Integer escapeQuotes;
    private Integer escapeSpecialCharacters;
    private String exportPattern;

    public Integer getEscapeQuotes() {
        return escapeQuotes;
    }

    public void setEscapeQuotes(Integer escapeQuotes) {
        this.escapeQuotes = escapeQuotes;
    }

    public Integer getEscapeSpecialCharacters() {
        return escapeSpecialCharacters;
    }

    public void setEscapeSpecialCharacters(Integer escapeSpecialCharacters) {
        this.escapeSpecialCharacters = escapeSpecialCharacters;
    }

    public String getExportPattern() {
        return exportPattern;
    }

    public void setExportPattern(String exportPattern) {
        this.exportPattern = exportPattern;
    }
}
