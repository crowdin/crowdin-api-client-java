package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchOrganizationConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private Boolean autoSubstitution;
    private Integer minRelevant;
    private List<String> expressions;
    private Long userId;
}
