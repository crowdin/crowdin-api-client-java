package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class StringTranslation {

    private Long id;
    private String text;
    private PluralCategoryName pluralCategoryName;
    private User user;
    private Integer rating;
    private String provider;
    private Long providerId;
    private Boolean isPreTranslated;
    private Integer matchRate;
    /**
     * One of {@code perfect}, {@code exact}, {@code fuzzy}
     */
    private String matchType;
    private Date createdAt;
    /**
     * Asset translations only
     */
    private String url;
    /**
     * Enterprise only
     */
    private Long workflowStepId;
    /**
     * Returned by Translations Search only
     */
    private Long projectId;
    /**
     * Returned by Translations Search only
     */
    private Long stringId;
    /**
     * Returned by Translations Search only
     */
    private String languageId;
}
