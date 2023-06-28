package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HtmlFileImportOptions extends ImportOptions {
    private List<String> excludedElements;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
