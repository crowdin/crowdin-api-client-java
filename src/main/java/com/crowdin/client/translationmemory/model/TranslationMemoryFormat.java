package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.EnumConverter;

public enum TranslationMemoryFormat implements EnumConverter<TranslationMemoryFormat> {

    TMX, CSV, XLSX;

    public static TranslationMemoryFormat from(String value) {
        return TranslationMemoryFormat.valueOf(value.toUpperCase());
    }

    @Override
    public String to(TranslationMemoryFormat v) {
        return v.name().toLowerCase();
    }
}
