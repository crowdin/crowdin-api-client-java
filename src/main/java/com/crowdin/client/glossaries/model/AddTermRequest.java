package com.crowdin.client.glossaries.model;

import lombok.Data;

@Data
public class AddTermRequest {

    private String languageId;
    private String text;
    private String description;
    private PartOfSpeech partOfSpeech;
    private Status status;
    private Type type;
    private Gender gender;
    private String note;
    private String url;
    private Long conceptId;
    @Deprecated
    private Long translationOfTermId;
}
