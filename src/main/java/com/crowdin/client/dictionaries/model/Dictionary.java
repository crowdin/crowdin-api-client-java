package com.crowdin.client.dictionaries.model;

import lombok.Data;

import java.util.List;

@Data
public class Dictionary {

    private String languageId;
    private List<String> words;
}