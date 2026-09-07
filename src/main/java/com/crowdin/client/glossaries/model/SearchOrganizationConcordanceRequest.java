package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchOrganizationConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private List<String> expressions;
    private Long userId;
}
