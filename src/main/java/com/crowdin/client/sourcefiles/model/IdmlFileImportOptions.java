package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Import options for IDML files. {@code contentSegmentation} and {@code srxStorageId} are inherited from {@link OtherFileImportOptions}.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IdmlFileImportOptions extends OtherFileImportOptions {

    private Boolean inlineHyperlinkText;
}
