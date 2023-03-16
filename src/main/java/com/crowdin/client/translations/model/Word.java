package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class Word {
    private String text;
    private List<Alignment> alignments;
}
