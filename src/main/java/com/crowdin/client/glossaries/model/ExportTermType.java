package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Term type filter values accepted by the glossary export endpoint
 * (sent as upper-case values, e.g. {@code FULL_FORM}).
 */
public enum ExportTermType implements EnumConverter<ExportTermType> {
    FULL_FORM, ACRONYM, ABBREVIATION, SHORT_FORM, PHRASE, VARIANT;

    public static ExportTermType from(String value) {
        return ExportTermType.valueOf(value.toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public String to(ExportTermType v) {
        return v.name();
    }
}
