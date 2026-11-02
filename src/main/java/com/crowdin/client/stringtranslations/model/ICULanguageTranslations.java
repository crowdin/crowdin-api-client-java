package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class ICULanguageTranslations implements LanguageTranslations {
    private Long stringId;
    private String contentType;
    private Long translationId;
    private String text;
    private User user;
    private Date createdAt;
    /**
     * Translation provider, {@code null} when the translation was added manually
     */
    private String provider;
    /**
     * Provider specific identifier (a TM, an AI prompt or an MT engine), {@code null} when there is none
     */
    private Long providerId;
    private Boolean isPreTranslated;
    /**
     * Translation Memory match rate in percent, {@code null} when no TM match was available
     */
    private Integer matchRate;
    /**
     * One of {@code perfect}, {@code exact}, {@code fuzzy}; {@code null} when no TM match was available
     */
    private String matchType;
    /**
     * One of {@code inProgress}, {@code failed}, {@code passed}
     */
    private String qaIssuesStatus;
}
