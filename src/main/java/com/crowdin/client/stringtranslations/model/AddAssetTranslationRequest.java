package com.crowdin.client.stringtranslations.model;

import lombok.Data;

/**
 * Adds a translation for an asset file (file-based projects only).
 */
@Data
public class AddAssetTranslationRequest {

    private Long fileId;
    private String languageId;
    private Long storageId;
}
