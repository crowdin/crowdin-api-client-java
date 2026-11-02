package com.crowdin.client.translations.model;

import lombok.Data;

/**
 * Single translation to validate against QA checks. The request body is a list of these items.
 */
@Data
public class ValidateQaChecksRequest {

    private Long stringId;
    private String languageId;
    private String text;
    /**
     * Plural form: {@code zero}, {@code one}, {@code two}, {@code few}, {@code many} or {@code other}
     */
    private String pluralCategoryName;
}
