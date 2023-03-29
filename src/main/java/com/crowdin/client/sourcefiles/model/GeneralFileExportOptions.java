package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class GeneralFileExportOptions extends ExportOptions {

    private String exportPattern;
}
