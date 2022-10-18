package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

import java.util.List;

@Data
public class MtTranslateResponse {

    private String sourceLanguageId;
    private String targetLanguageId;
    private List<String> strings;
    private List<String> translations;
}
