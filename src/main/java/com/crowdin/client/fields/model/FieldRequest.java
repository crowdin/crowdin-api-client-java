package com.crowdin.client.fields.model;

import lombok.Data;

import java.util.List;

@Data
public class FieldRequest {
    private String name;
    private String slug;
    private String type;
    private String description;
    private List<String> entities;
    private Config config;

}
