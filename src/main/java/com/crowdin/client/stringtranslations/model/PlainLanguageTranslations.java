package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class PlainLanguageTranslations implements LanguageTranslations {
    private Long stringId;
    private String contentType;
    private Long translationId;
    private String text;
    private User user;
    private Date createdAt;
}
