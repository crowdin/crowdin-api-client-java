package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ImportTranslationsRequest {

    private Long storageId;
    private List<String> languageIds;
    private Long fileId;
    private Boolean importEqSuggestions;
    private Boolean autoApproveImported;
    private Boolean translateHidden;
    private Boolean addToTm;

}
