package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.List;

@Data
public class XmlFileImportOptions extends ImportOptions {

    private boolean translateContent;
    private boolean translateAttributes;
    private boolean contentSegmentation;
    private List<String> translatableElements;
}
