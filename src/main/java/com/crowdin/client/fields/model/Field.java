package com.crowdin.client.fields.model;

import com.crowdin.client.fields.model.enums.EntityType;
import com.crowdin.client.fields.model.enums.FieldType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Field  {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private FieldType type;
    private Config config;
    private List<EntityType> entities;
    private Date createdAt;
    private Date updatedAt;

}
