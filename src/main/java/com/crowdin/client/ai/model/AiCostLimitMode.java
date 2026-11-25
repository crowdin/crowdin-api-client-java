package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum AiCostLimitMode implements EnumConverter<AiCostLimitMode> {
    CUSTOM, BLOCKED, UNLIMITED;

    public static AiCostLimitMode from(String value) {
        return AiCostLimitMode.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(AiCostLimitMode v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
