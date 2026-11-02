package com.crowdin.client.applications.integrations.model;

import lombok.Data;

@Data
public class UpdateIntegrationSyncSettingsRequest {

    private Long projectId;
    private IntegrationSyncProvider provider;
    /**
     * Either a map of Crowdin file id to a list of language ids (e.g. {@code Map<String, List<String>>})
     * or a list of file nodes (e.g. {@code List<IntegrationFile>})
     */
    private Object files;
}
