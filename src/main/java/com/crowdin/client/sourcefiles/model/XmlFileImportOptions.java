package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class XmlFileImportOptions extends ImportOptions {

    private Boolean translateContent;
    private Boolean translateAttributes;
    private Boolean contentSegmentation;
    private List<String> translatableElements;
}
