package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Glossary {

    private Long id;
    private String name;
    private Long groupId;
    private Long userId;
    private Integer terms;
    private List<String> languageIds;
    private List<Long> projectIds;
    private Date createdAt;
}
