package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class Alignment {
    private String sourceWord;
    private String sourceLemma;
    private String targetWord;
    private String targetLemma;
    private Integer match;
    private Integer probability;
}
