package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JavaScriptFileExportOptions extends ExportOptions {

    private ExportQuotes exportQuotes;
    private String exportPattern;
}
