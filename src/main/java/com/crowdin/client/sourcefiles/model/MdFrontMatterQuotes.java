package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

/**
 * Quotes used for front matter values in exported Markdown files.
 */
public enum MdFrontMatterQuotes implements EnumConverter<MdFrontMatterQuotes> {

    AUTO, SINGLE, DOUBLE;

    @JsonCreator
    public static MdFrontMatterQuotes from(String value) {
        return MdFrontMatterQuotes.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @JsonValue
    @Override
    public String to(MdFrontMatterQuotes v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
