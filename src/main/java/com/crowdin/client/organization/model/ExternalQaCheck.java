package com.crowdin.client.organization.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExternalQaCheck {

    private Long id;
    private String name;
    private String description;
    private Map<String, Object> config;
    private Date createdAt;
    private Date updatedAt;
}
