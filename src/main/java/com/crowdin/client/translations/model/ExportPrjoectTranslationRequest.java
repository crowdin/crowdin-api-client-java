package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportPrjoectTranslationRequest {

    private String targetLanguageId;
    private String format;
    private List<Long> branchIds;
    private List<Long> directoryIds;
    private List<Long> fileIds;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Integer exportWithMinApprovalsCount;

}
