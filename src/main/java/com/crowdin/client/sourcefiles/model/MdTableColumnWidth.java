package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Table formatting in exported Markdown/MDX files.
 */
public enum MdTableColumnWidth implements EnumConverter<MdTableColumnWidth> {

    CONSOLIDATE, EVENLY_DISTRIBUTE_CELLS;

    @JsonCreator
    public static MdTableColumnWidth from(String value) {
        return MdTableColumnWidth.valueOf(value.toUpperCase());
    }

    @JsonValue
    @Override
    public String to(MdTableColumnWidth v) {
        return v.name().toLowerCase();
    }
}
