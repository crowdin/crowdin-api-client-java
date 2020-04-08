package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class AddStringTranslationRequest {

    private Long stringId;
    private String languageId;
    private String text;
    private PluralCategoryName pluralCategoryName;
}
