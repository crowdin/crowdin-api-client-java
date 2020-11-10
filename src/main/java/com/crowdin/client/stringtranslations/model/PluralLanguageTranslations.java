package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PluralLanguageTranslations implements LanguageTranslations {
    private Long stringId;
    private String contentType;
    private List<Plurals> plurals;

    @Data
    public static class Plurals {
        private Long translationId;
        private String text;
        private String pluralForm;
        private User user;
        private Date createdAt;
    }
}
