package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GeneralFileExportOptions extends ExportOptions {

    private String exportPattern;
}
