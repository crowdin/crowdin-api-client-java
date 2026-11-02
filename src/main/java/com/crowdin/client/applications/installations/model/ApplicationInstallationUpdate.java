package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApplicationInstallationUpdate {

    private String manifestHash;
    private Map<String, Object> latestManifest;
    private List<String> addedScopes;
    private List<String> removedScopes;
    private List<Map<String, Object>> addedModules;
    private List<Map<String, Object>> removedModules;
    private List<Map<String, Object>> changedModules;
    private Map<String, Change> changedEvents;
    private Change baseUrlChanged;
    private Change authenticationTypeChanged;
    private Boolean hasChanges;

    @Data
    public static class Change {
        private String from;
        private String to;
    }
}
