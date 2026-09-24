package com.crowdin.client.users.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class TranslatorRole {
    private TranslatorRoleName name;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private TranslatorRolePermissions permissions;
}
