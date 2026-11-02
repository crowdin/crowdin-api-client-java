package com.crowdin.client.translationstatus.model;

import lombok.Data;

@Data
public class QaCheck {

    private Long stringId;
    private String languageId;
    private Category category;
    private String categoryDescription;
    private Validation validation;
    private String validationDescription;
    private Long pluralId;
    /**
     * Returned by Validate QA Checks only
     */
    private String pluralCategoryName;
    private String text;
    /**
     * Returned by Validate QA Checks only
     */
    private String translation;
}
