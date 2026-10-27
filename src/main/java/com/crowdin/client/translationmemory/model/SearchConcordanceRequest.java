package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private Boolean autoSubstitution;
    private Integer minRelevant;
    /**
     * Expressions to search. Can't be used with {@link #expression} in the same request
     */
    private List<String> expressions;
    /**
     * @deprecated use {@link #expressions} instead. Can't be used with {@link #expressions} in the same request
     */
    @Deprecated
    private String expression;
}
