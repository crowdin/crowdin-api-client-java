package com.crowdin.client.styleguide.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StyleGuide {
    private Long id;
    private String name;
    private String aiInstructions;
    private Long userId;
    private List<String> languageIds;
    private List<Long> projectIds;
    private Boolean isShared;
    private String webUrl;
    private String downloadLink;
    private Date createdAt;
    private Date updatedAt;
}
