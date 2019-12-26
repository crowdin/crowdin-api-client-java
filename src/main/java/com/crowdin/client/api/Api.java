package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.common.Settings;
import com.fasterxml.jackson.core.type.TypeReference;

abstract class Api {

    Settings settings;

    Api(Settings settings) {
        this.settings = settings;
    }

    <R> CrowdinRequestBuilder<R> getBuilderWithSettings(TypeReference<R> responseType) {
        return CrowdinRequestBuilder.builder(settings.getBaseUrl(), responseType)
                .token(settings.getToken());
    }

    CrowdinRequestBuilder getBuilderWithSettings() {
        return getBuilderWithSettings(null);
    }
}
