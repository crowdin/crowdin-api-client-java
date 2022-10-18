package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Date;

@Data
public class Term {

    private Long id;
    private Long userId;
    private Long glossaryId;
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
    private String lemma;
    private Date createdAt;
    private Date updatedAt;
}
