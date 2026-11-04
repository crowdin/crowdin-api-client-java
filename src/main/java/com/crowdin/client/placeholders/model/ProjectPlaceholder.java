package com.crowdin.client.placeholders.model;

import lombok.Data;

import java.util.List;

/**
 * A custom placeholder of the organization, with the settings it has in the project.
 */
@Data
public class ProjectPlaceholder {

    private Long id;
    private Long customPlaceholderId;
    private ProjectPlaceholderType type;
    private Integer index;
    private Boolean isBlocking;
    private List<String> formats;
}
