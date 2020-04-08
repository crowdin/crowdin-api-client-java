package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class BuildProjectTranslationRequest {

    private Long branchId;
    private List<String> targetLanguageIds;
}
