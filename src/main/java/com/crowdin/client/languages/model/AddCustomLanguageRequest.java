package com.crowdin.client.languages.model;

import lombok.Data;

import java.util.List;

@Data
public class AddCustomLanguageRequest {

    private String name;
    private String code;
    private String localeCode;
    private TextDirection textDirection;
    private List<String> pluralCategoryNames;
    private String threeLettersCode;
    private String twoLettersCode;
    private String dialectOf;
}
