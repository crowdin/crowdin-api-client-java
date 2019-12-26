package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Status;
import com.crowdin.common.models.Translation;
import com.crowdin.common.request.BuildTranslationPayload;
import com.crowdin.common.request.TranslationPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class TranslationsApi extends Api {

    public TranslationsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Translation>> getTranslations(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATIONS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Translation>> buildTranslation(String projectId, BuildTranslationPayload translationPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATIONS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(translationPayload);
    }

    //todo multiply result need to handle
    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getTranslationRaw(String projectId, String buildId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.DOWNLOAD_TRANSLATION)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, buildId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Translation>> getTranslationInfo(String projectId, String buildId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Translation>>() {
        })
                .path(Path.PROJECT_TRANSLATION)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, buildId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadTranslation(String projectId, String languageId, TranslationPayload translationPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.UPLOAD_LANGUAGE_TRANSLATIONS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .requestBody(translationPayload)
                .pathParams(projectId, languageId);
    }
}
