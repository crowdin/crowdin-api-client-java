package com.crowdin.client.organization.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExternalQaCheck {

    private Long id;
    private String name;
    private String description;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, Object> config;
    private Date createdAt;
    private Date updatedAt;
}
