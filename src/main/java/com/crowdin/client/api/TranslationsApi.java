package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Status;
import com.crowdin.common.models.Translation;
import com.crowdin.common.request.BuildTranslationPayload;
import com.crowdin.common.request.LenguageTranslationPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class TranslationsApi extends Api {

    public TranslationsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Translation>> getTranslation(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATIONS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<Translation>> getTranslation(String userId, String projectId, BuildTranslationPayload translationPayload) {
        return getBuilderWithSettings(new TypeReference<Page<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATIONS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(translationPayload);
    }

    //todo multiply result need to handle
    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getTranslationRaw(String userId, String projectId, String buildId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.DOWNLOAD_TRANSLATION)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, buildId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Translation>> getTranslationInfo(String userId, String projectId, String buildId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATION)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, buildId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadLanguageTranslation(String userId, String projectId, String languageId, LenguageTranslationPayload lenguageTranslationPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.UPLOAD_LANGUAGE_TRANSLATIONS)
                .method(HttpClient.HttpMethod.POST)
                .requestBody(lenguageTranslationPayload)
                .pathParams(userId, projectId, languageId);
    }
}
