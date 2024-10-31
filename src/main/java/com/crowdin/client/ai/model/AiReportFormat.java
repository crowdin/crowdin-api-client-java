package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AiReportFormat implements EnumConverter<AiReportFormat> {
    JSON, CSV;

    public static AiReportFormat from(String value) {
        return AiReportFormat.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(AiReportFormat v) {
        return v.name().toLowerCase();
    }
}
