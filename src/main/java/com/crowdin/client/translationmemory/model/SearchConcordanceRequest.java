package com.crowdin.client.translationmemory.model;

import lombok.Data;

@Data
public class SearchConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private Boolean autoSubstitution;
    private Integer minRelevant;
    /**
     * @deprecated
     */
    private String expression;
}
