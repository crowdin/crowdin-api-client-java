package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Import options for Visio (vsdx) files. Responses for vsdx files are deserialized into {@link DocxFileImportOptions}, which contains the same fields.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VsdxFileImportOptions extends ImportOptions {

    private Boolean cleanTagsAggressively;
    private Boolean translateHyperlinkUrls;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
