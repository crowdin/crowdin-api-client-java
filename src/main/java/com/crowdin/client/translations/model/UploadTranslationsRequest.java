package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class UploadTranslationsRequest {

    private Long storageId;
    private Long fileId;
    private Boolean importDuplicates;
    private Boolean importEqSuggestions;
    private Boolean autoApproveImported;
    private Boolean markAddedTranslationsAsDone;
}
