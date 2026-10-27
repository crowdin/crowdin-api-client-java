package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AiCostLimitMode implements EnumConverter<AiCostLimitMode> {
    CUSTOM, BLOCKED, UNLIMITED;

    public static AiCostLimitMode from(String value) {
        return AiCostLimitMode.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(AiCostLimitMode v) {
        return v.name().toLowerCase();
    }
}
