package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class XmlFileImportOptions extends ImportOptions {

    private Boolean translateContent;
    private Boolean translateAttributes;
    private Boolean contentSegmentation;
    /**
     * Returned in responses only.
     */
    private Boolean customSegmentation;
    private List<String> translatableElements;
    private List<String> inlineTags;
    private Long srxStorageId;
}
