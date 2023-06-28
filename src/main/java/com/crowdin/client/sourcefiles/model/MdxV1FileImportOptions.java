package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MdxV1FileImportOptions extends ImportOptions {
    private List<String> excludedFrontMatterElements;
    private Boolean excludeCodeBlocks;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
