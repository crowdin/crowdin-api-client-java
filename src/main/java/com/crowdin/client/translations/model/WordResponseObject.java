package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class WordResponseObject {
    private List<Word> words;
}
