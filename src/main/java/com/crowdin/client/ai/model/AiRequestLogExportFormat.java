package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AiRequestLogExportFormat implements EnumConverter<AiRequestLogExportFormat> {
    CSV;

    public static AiRequestLogExportFormat from(String value) {
        return AiRequestLogExportFormat.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(AiRequestLogExportFormat v) {
        return v.name().toLowerCase();
    }
}
