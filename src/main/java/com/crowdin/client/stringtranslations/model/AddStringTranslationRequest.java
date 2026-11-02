package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class AddStringTranslationRequest {

    private Long stringId;
    private String languageId;
    private String text;
    private PluralCategoryName pluralCategoryName;
    private Boolean addToTm;
    /**
     * Translation provider, e.g. {@code tm}, {@code global_tm}, {@code google}, {@code microsoft}, {@code crowdin},
     * {@code deepl}, {@code amazon}, {@code google_automl}, {@code modernmt}, {@code custom_mt}, {@code ai}
     */
    private String provider;
    private Long providerId;
    private Boolean isPreTranslated;
}
