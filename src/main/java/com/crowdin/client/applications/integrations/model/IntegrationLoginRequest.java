package com.crowdin.client.applications.integrations.model;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
public class IntegrationLoginRequest {

    private Long projectId;
    /**
     * Login form field values keyed by the field key (see Integration Login Form Fields).
     * Only the field names are included in {@code toString()}
     */
    @ToString.Exclude
    private Map<String, Object> credentials;

    /**
     * {@code toString()} view of {@link #credentials}: only the field names, never the values
     */
    @ToString.Include(name = "credentials")
    private Object credentialsKeysForToString() {
        return this.credentials == null ? null : this.credentials.keySet();
    }
}
