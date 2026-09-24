package com.crowdin.client.users.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.crowdin.client.core.model.LanguageAccessRule;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
public class TranslatorRolePermissions {
    private boolean allLanguages;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, LanguageAccessRule> languagesAccess;
}
