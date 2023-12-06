package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {

    private UserPermissions value;
    private int[] ids;

}
