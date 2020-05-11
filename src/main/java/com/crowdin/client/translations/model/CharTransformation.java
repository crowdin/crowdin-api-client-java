package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum CharTransformation implements EnumConverter<CharTransformation> {
    ASIAN("asian"), CYRILLIC("cyrillic"), EUROPEAN("european"), ARABIC("arabic");

    private final String value;

    CharTransformation(String value) {
        this.value = value;
    }

    public static CharTransformation from(String value) {
        for (CharTransformation m : CharTransformation.values()) {
            if (m.value.equals(value)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String to(CharTransformation v) {
        return v.value;
    }
}
