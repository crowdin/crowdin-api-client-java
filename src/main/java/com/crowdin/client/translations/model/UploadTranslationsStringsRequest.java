package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class UploadTranslationsStringsRequest {

    private Long storageId;
    private Long branchId;
    private Boolean importEqSuggestions;
    private Boolean autoApproveImported;
    private Boolean translateHidden;
}
