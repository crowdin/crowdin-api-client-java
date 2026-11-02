package com.crowdin.client.applications.integrations.model;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
public class UpdateIntegrationSettingsRequest {

    private Long projectId;
    /**
     * Integration settings. Only the setting names are included in {@code toString()}
     */
    @ToString.Exclude
    private Map<String, Object> config;

    /**
     * {@code toString()} view of {@link #config}: only the field names, never the values
     */
    @ToString.Include(name = "config")
    private Object configKeysForToString() {
        return this.config == null ? null : this.config.keySet();
    }
}
