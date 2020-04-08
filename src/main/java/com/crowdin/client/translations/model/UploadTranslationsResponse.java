package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class UploadTranslationsResponse {

    private Long projectId;
    private Long storageId;
    private String languageId;
    private Long fileId;
}
