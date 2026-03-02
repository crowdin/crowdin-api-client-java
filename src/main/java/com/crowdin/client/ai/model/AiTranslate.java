package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiTranslate {
    private String sourceLanguageId;
    private String targetLanguageId;
    private List<String> translations;
}
