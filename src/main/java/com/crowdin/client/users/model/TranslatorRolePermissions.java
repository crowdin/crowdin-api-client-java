package com.crowdin.client.users.model;

import com.crowdin.client.core.model.LanguageAccessRule;
import lombok.Data;

import java.util.Map;

@Data
public class TranslatorRolePermissions {
    private boolean allLanguages;
    private Map<String, LanguageAccessRule> languagesAccess;
}
