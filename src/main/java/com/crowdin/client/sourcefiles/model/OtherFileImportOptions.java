package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Import options for file formats without a dedicated options class.
 * Format-specific import options returned by the API for html, fm_html, md, mdx, webxml, idml and similar
 * formats are deserialized into subclasses of this class.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OtherFileImportOptions extends ImportOptions {

    private Boolean contentSegmentation;
    /**
     * Returned in responses only.
     */
    private Boolean customSegmentation;
    private Long srxStorageId;
}
