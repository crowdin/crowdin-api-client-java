package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Import options for HTML files. {@code contentSegmentation} and {@code srxStorageId} are inherited
 * from {@link OtherFileImportOptions}.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HtmlFileImportOptions extends OtherFileImportOptions {
    private List<String> excludedElements;
    private List<String> inlineTags;
    private Boolean hideAttributeValues;
}
