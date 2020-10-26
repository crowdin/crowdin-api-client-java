package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportProjectTranslationRequest {

    private String targetLanguageId;
    private String format;
    private List<Long> labelIds;
    private List<Long> branchIds;
    private List<Long> directoryIds;
    private List<Long> fileIds;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;

}
