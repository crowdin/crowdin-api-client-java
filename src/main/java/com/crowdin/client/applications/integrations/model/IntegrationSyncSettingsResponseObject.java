package com.crowdin.client.applications.integrations.model;

import lombok.Data;

@Data
public class IntegrationSyncSettingsResponseObject {

    /**
     * Either a map of Crowdin file id to a list of language ids or a list of file nodes
     */
    private Object data;
}
