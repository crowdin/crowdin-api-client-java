package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Concept {

    private Long id;
    private Long userId;
    private Long glossaryId;
    private String subject;
    private String definition;
    private Boolean translatable;
    private String note;
    private String url;
    private String figure;
    private List<ConceptLanguagesDetails> languagesDetails;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class  ConceptLanguagesDetails {
        private String languageId;
        private Long userId;
        private String definition;
        private String note;
        private Date createdAt;
        private Date updatedAt;
    }
}
