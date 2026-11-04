package com.crowdin.client.placeholders.model;

import lombok.Data;

import java.util.List;

/**
 * A placeholder Crowdin ships, turned on or off per project.
 */
@Data
public class SystemPlaceholder {

    /**
     * Key of the system placeholder (e.g. printfSpecifier). Kept as a string because the catalogue grows over time.
     */
    private String id;
    private Boolean isEnabled;
    private String label;
    private List<String> examples;
    private String description;
}
