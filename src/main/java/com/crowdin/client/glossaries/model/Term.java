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
    private String lemma;
    private Date createdAt;
    private Date updatedAt;
}
