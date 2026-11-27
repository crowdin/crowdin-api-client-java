package com.crowdin.client.applications.integrations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum IntegrationSyncProvider implements EnumConverter<IntegrationSyncProvider> {

    CROWDIN, INTEGRATION;

    public static IntegrationSyncProvider from(String value) {
        return IntegrationSyncProvider.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(IntegrationSyncProvider value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
