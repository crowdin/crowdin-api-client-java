package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Marker used for unordered list bullets in exported Markdown/MDX files.
 */
public enum MdUnorderedListBullet implements EnumConverter<MdUnorderedListBullet> {

    ASTERISKS, PLUS, DASH;

    @JsonCreator
    public static MdUnorderedListBullet from(String value) {
        return MdUnorderedListBullet.valueOf(value.toUpperCase());
    }

    @JsonValue
    @Override
    public String to(MdUnorderedListBullet v) {
        return v.name().toLowerCase();
    }
}
