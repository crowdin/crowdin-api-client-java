package com.crowdin.client.core.model;

public enum EscapeSpecialCharsMode implements EnumConverter<EscapeSpecialCharsMode> {
    DO_NOT_ESCAPE(0),
    ESCAPE_BY_BACKSLASH(1);

    private final int value;

    EscapeSpecialCharsMode(int value) {
        this.value = value;
    }

    public static EscapeSpecialCharsMode from(String value) {
        return EscapeSpecialCharsMode.values()[Integer.parseInt(value)];
    }

    @Override
    public Object to(EscapeSpecialCharsMode v) {
        return v.value;
    }
}
