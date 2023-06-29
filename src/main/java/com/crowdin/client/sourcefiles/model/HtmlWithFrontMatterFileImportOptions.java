package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HtmlWithFrontMatterFileImportOptions extends ImportOptions {
    private List<String> excludedElements;
    private List<String> excludedFrontMatterElements;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
