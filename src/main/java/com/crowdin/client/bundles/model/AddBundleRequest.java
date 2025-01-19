package com.crowdin.client.bundles.model;

import lombok.Data;

import java.util.List;

@Data
public class AddBundleRequest {
    private String name;
    private String format;
    private List<String> sourcePatterns;
    private List<String> ignorePatterns;
    private String exportPattern;
    private Boolean isMultilingual;
    private Boolean includeProjectSourceLanguage;
    private Boolean includeInContextPseudoLanguage;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
}
