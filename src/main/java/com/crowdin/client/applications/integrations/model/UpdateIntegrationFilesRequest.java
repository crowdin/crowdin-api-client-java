package com.crowdin.client.applications.integrations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateIntegrationFilesRequest {

    private Long projectId;
    /**
     * Crowdin file id (as string key) to the list of language ids, e.g. {@code {"102": ["de", "fr"]}}
     */
    private Map<String, List<String>> files;
}
