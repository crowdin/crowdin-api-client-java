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
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class LanguagesApi extends Api {

    public LanguagesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Language>> getLanguages(String userId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Language>>() {
        })
                .path(Path.LANGUAGES)
                .pageable(pageable)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId);
    }

    public CrowdinRequestBuilder<Page<Language>> createLanguage(String userId, LanguagePayload languagePayload) {
        return getBuilderWithSettings(new TypeReference<Page<Language>>() {
        })
                .path(Path.LANGUAGES)
                .requestBody(languagePayload)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Language>> getLanguage(String userId, String languageId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Language>>() {
        })
                .path(Path.LANGUAGE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, languageId);
    }

    public CrowdinRequestBuilder<String> deleteLanguage(String userId, String languageId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.LANGUAGE)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, languageId);
    }

    public CrowdinRequestBuilder<String> updateLanguage(String userId, String languageId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.LANGUAGE)
                .requestBody(updateOperation)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, languageId);
    }
}
