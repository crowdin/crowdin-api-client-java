package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Term gender filter values accepted by the glossary export endpoint
 * (sent as upper-case values, e.g. {@code MASCULINE}).
 */
public enum ExportTermGender implements EnumConverter<ExportTermGender> {
    MASCULINE, FEMININE, NEUTER, COMMON, OTHER;

    public static ExportTermGender from(String value) {
        return ExportTermGender.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ExportTermGender v) {
        return v.name();
    }
}
