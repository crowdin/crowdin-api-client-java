package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MachineTranslation {

    private Long id;
    private Long groupId;
    private String name;
    private Type type;
    private Map<String, String> credentials;
    private List<Long> projectIds;
    private List<String> supportedLanguageIds;
    private Map<String, List<String>> supportedLanguagePairs;
    private List<String> enabledLanguageIds;
    private List<Long> enabledProjectIds;
    private Boolean isEnabled;
}
