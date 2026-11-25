package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum AiRequestLogStatus implements EnumConverter<AiRequestLogStatus> {
    PENDING, SUCCESS, ERROR, TIMEOUT;

    public static AiRequestLogStatus from(String value) {
        return AiRequestLogStatus.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(AiRequestLogStatus value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
