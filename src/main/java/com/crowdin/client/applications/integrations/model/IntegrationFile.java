package com.crowdin.client.applications.integrations.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * File or folder node of an integration (Crowdin files, integration files, sync settings files).
 * The API returns either camelCase ({@code parentId}, {@code type}) or snake_case ({@code parent_id}, {@code node_type})
 * keys depending on the node, so both variants are mapped.
 */
@Data
public class IntegrationFile {

    private String id;
    private String name;
    private String parentId;
    /**
     * Parent node identifier returned as {@code parent_id}
     */
    @JsonProperty("parent_id")
    private String parentIdSnakeCase;
    private String type;
    /**
     * Node type returned as {@code node_type}
     */
    @JsonProperty("node_type")
    private String nodeType;
    private Boolean schedule;
}
