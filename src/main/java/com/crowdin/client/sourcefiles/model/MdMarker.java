package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Marker used for strong and emphasis text in exported Markdown/MDX files.
 */
public enum MdMarker implements EnumConverter<MdMarker> {

    ASTERISK, UNDERSCORE;

    @JsonCreator
    public static MdMarker from(String value) {
        return MdMarker.valueOf(value.toUpperCase());
    }

    @JsonValue
    @Override
    public String to(MdMarker v) {
        return v.name().toLowerCase();
    }
}
