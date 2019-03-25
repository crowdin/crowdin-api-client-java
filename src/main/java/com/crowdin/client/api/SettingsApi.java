package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class SettingsApi extends Api {

    public SettingsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Settings>> getSettings(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Settings>>() {
        })
                .path(Path.SETTINGS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Settings>> updateSettings(String userId, String projectId, List<PatchOperation> patchOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Settings>>() {
        })
                .path(Path.SETTINGS)
                .method(HttpClient.HttpMethod.PATCH)
                .requestBody(patchOperations)
                .pathParams(userId, projectId);
    }
}
