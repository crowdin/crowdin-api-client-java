package com.crowdin.client.styleguide.model;

import lombok.Data;

import java.util.List;

@Data
public class AddStyleGuideRequest {
    private String name;
    private String aiInstructions;
    private List<String> languageIds;
    private List<Long> projectIds;
    private Boolean isShared;
    private Long storageId;
}
