package com.crowdin.client.languages.model;

import lombok.Data;

import java.util.List;

@Data
public class Language {

    private String id;
    private String name;
    private String editorCode;
    private String twoLettersCode;
    private String threeLettersCode;
    private String locale;
    private String androidCode;
    private String osxCode;
    private String osxLocale;
    private List<String> pluralCategoryNames;
    private String pluralRules;
    private List<String> pluralExamples;
    private TextDirection textDirection;
    private String dialectOf;
}
