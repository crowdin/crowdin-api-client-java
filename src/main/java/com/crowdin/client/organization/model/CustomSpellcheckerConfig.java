package com.crowdin.client.organization.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomSpellcheckerConfig {

    private String identifier;
    private String key;
    private Boolean realTimeCheckEnabled;
    private List<String> enabledLanguageIds;
}
