package com.crowdin.client.placeholders.model;

import lombok.Data;

@Data
public class AddOrganizationPlaceholderRequest {

    private String definition;
    private String description;
    private String argumentDelimiter;
}
