package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchOrganizationConcordanceRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private List<String> expressions;
    /**
     * Crowdin only. Owner (user) whose glossaries to search, defaults to your own account
     */
    private Long userId;
}
