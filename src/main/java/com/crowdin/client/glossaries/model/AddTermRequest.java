package com.crowdin.client.glossaries.model;

import lombok.Data;

@Data
public class AddTermRequest {

    private String languageId;
    private String text;
    private String description;
    private PartOfSpeech partOfSpeech;
    private Long translationOfTermId;
}
