package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Language;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.LanguagePayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class LanguagesApi extends Api {

    public LanguagesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Language>> getLanguages(Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Language>>() {
        })
                .path(Path.LANGUAGES)
                .pageable(pageable)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams();
    }

    public CrowdinRequestBuilder<SimpleResponse<Language>> createLanguage(LanguagePayload languagePayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Language>>() {
        })
                .path(Path.LANGUAGES)
                .requestBody(languagePayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams();
    }

    public CrowdinRequestBuilder<SimpleResponse<Language>> getLanguage(String languageId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Language>>() {
        })
                .path(Path.LANGUAGE)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(languageId);
    }

    public CrowdinRequestBuilder deleteLanguage(String languageId) {
        return getBuilderWithSettings()
                .path(Path.LANGUAGE)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(languageId);
    }

    public CrowdinRequestBuilder<String> updateLanguage(String languageId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.LANGUAGE)
                .requestBody(updateOperation)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(languageId);
    }
}
