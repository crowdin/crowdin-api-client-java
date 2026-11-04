package com.crowdin.client.placeholders.model;

import lombok.Data;

/**
 * Custom placeholder defined by the organization (Enterprise "Custom Placeholder" resource).
 * Not to be confused with {@link com.crowdin.client.ai.model.CustomPlaceholder} used by AI prompts.
 */
@Data
public class OrganizationPlaceholder {

    private Long id;
    private String definition;
    private String description;
    private String argumentDelimiter;
}
