package com.crowdin.client.organization.model;

import lombok.Data;

import java.util.Date;

@Data
public class CustomSpellchecker {

    private Long id;
    private String name;
    private CustomSpellcheckerConfig config;
    private Date createdAt;
    private Date updatedAt;
}
