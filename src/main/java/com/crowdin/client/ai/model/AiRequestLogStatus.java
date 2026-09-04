package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AiRequestLogStatus implements EnumConverter<AiRequestLogStatus> {
    PENDING, SUCCESS, ERROR, TIMEOUT;

    public static AiRequestLogStatus from(String value) {
        return AiRequestLogStatus.valueOf(value.toUpperCase());
    }

    @Override
    public String to(AiRequestLogStatus value) {
        return value.name().toLowerCase();
    }
}
