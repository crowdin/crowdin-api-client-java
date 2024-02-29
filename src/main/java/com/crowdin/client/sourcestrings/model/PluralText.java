package com.crowdin.client.sourcestrings.model;

import lombok.Data;

@Data
public class PluralText {

    private String zero;
    private String one;
    private String two;
    private String few;
    private String many;
    private String other;
}
