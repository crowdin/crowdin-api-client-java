package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ReportsFormat implements EnumConverter<ReportsFormat> {

    XLSX, CSV, JSON;

    public static ReportsFormat from(String value) {
        return ReportsFormat.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ReportsFormat v) {
        return v.name().toLowerCase();
    }
}
