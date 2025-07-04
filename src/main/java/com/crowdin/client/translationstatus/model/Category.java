package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Category implements EnumConverter<Category> {

    EMPTY, VARIABLES, TAGS, PUNCTUATION, SYMBOL_REGISTER,
    SPACES, SIZE, SPECIAL_SYMBOLS, WRONG_TRANSLATION, SPELLCHECK, ICU, DUPLICATE;

    public static Category from(String value) {
        return Category.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Category v) {
        return v.name().toLowerCase();
    }
}
