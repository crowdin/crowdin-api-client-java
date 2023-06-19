package com.crowdin.client.users.model;

import lombok.Data;

@Data
public class TranslatorRole {
    private TranslatorRoleName name;
    private TranslatorRolePermissions permissions;
}
