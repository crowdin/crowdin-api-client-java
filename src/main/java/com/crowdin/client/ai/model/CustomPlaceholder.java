package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class CustomPlaceholder {
    private Long id;
    private String description;
    private String placeholder;
    private String value;
    private Date createdAt;
    private Date updatedAt;
}
