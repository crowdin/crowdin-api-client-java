package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Term status filter values accepted by the glossary export endpoint
 * (sent as upper-case values, e.g. {@code NOT_RECOMMENDED}).
 */
public enum ExportTermStatus implements EnumConverter<ExportTermStatus> {
    PREFERRED, ADMITTED, NOT_RECOMMENDED, OBSOLETE, DRAFT;

    public static ExportTermStatus from(String value) {
        return ExportTermStatus.valueOf(value.toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public String to(ExportTermStatus v) {
        return v.name();
    }
}
