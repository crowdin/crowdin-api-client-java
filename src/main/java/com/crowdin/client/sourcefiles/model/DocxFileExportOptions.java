package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Export options for Office documents. {@code exportPattern} is inherited from {@link GeneralFileExportOptions}.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DocxFileExportOptions extends GeneralFileExportOptions {

    private Boolean allowWordStyleOptimization;
    private Boolean translateExcelExcludeColors;
}
