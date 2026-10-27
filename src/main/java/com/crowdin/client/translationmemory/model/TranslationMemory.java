package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TranslationMemory {

    private Long id;
    private Long groupId;
    private Long userId;
    private String name;
    private String languageId;
    private List<String> languageIds;
    private Integer segmentsCount;
    private List<Long> defaultProjectIds;
    private List<Long> projectIds;
    private Boolean isShared;
    private String webUrl;
    private Date createdAt;
}
