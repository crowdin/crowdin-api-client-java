package com.crowdin.client.glossaries.model;

import lombok.Data;

@Data
public class SearchConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private String expression;
}
