package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class UploadTranslationsStringsResponse {

    private Long projectId;
    private Long storageId;
    private String languageId;
    private Long branchId;
}
