package com.crowdin.client.bundles.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Bundle {

    private Long id;
    private String name;
    private String format;
    private List<String> sourcePatterns;
    private List<String> ignorePatterns;
    private String exportPattern;
    private boolean isMultilingual;
    private Boolean includeProjectSourceLanguage;
    private Boolean includeInContextPseudoLanguage;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private String webUrl;
    private Date createdAt;
    private Date updatedAt;
}
