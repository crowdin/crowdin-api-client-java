package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class PlainLanguageTranslations implements LanguageTranslations {
    private Long stringId;
    private String contentType;
    private Long translationId;
    private String text;
}
