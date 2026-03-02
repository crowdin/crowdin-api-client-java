package com.crowdin.client.translationstatus.model;

import lombok.Data;

import java.util.List;

@Data
public class QaCheckRevalidationRequest {

    private List<String> qaCheckCategories;
    private List<String> languageIds;
    private Boolean failedOnly;
}
