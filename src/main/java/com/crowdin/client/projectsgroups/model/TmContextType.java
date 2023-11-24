package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.stream.Stream;

public enum TmContextType implements EnumConverter<TmContextType> {
    SEGMENT_CONTEXT("segmentContext"), AUTO("auto"), PREV_AND_NEXT_SEGMENT("prevAndNextSegment");

    private final String val;

    TmContextType(String val) {
        this.val = val;
    }

    public static TmContextType from(String value) {
        return Stream.of(TmContextType.values()).filter(e -> e.val.equals(value)).findFirst().orElse(null);
    }

    @Override
    public String to(TmContextType v) {
        return v.val;
    }
}
