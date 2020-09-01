package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class PropertyFileExportOptions extends ExportOptions {

    private Integer escapeQuotes;
    private Integer escapeSpecialCharacters;
    private String exportPattern;
}
