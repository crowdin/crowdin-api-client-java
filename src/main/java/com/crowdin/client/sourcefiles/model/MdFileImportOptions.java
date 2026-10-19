package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Import options for Markdown files. {@code contentSegmentation} and {@code srxStorageId} are inherited from {@link OtherFileImportOptions}.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MdFileImportOptions extends OtherFileImportOptions {

    private List<String> excludedFrontMatterElements;
    private Boolean excludeCodeBlocks;
    private List<String> inlineTags;
}
