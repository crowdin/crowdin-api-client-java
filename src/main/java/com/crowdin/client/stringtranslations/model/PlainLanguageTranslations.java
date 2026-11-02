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
    private String provider;
    private Long providerId;
    private Boolean isPreTranslated;
    private Integer matchRate;
    /**
     * One of {@code perfect}, {@code exact}, {@code fuzzy}
     */
    private String matchType;
    /**
     * One of {@code inProgress}, {@code failed}, {@code passed}
     */
    private String qaIssuesStatus;
}
