package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

import java.util.List;

@Data
public class AddMachineTranslationRequest {

    private String name;
    private Type type;
    private Credentials credentials;
    private Long groupId;
    private List<String> enabledLanguageIds;
    private List<Long> enabledProjectIds;
    private Boolean isEnabled;

}
