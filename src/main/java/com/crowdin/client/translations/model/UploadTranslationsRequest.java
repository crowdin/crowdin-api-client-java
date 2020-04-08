package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class UploadTranslationsRequest {

    private Long storageId;
    private Long fileId;
    private boolean importDuplicates;
    private boolean importEqSuggestions;
    private boolean autoApproveImported;
    private boolean markAddedTranslationsAsDone;
}
