package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.StorageDto;
import com.crowdin.common.models.Image;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class ImagesApi extends Api {
    public ImagesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Image>> uploadBackground(String userId, String projectId, StorageDto storage) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Image>>() {
        })
                .path(Path.BACKGROUND)
                .method(HttpClient.HttpMethod.PUT)
                .pathParams(userId, projectId)
                .requestBody(storage);
    }

    public CrowdinRequestBuilder<String> deleteBackground(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.BACKGROUND)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Image>> uploadLogo(String userId, String projectId, StorageDto storage) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Image>>() {
        })
                .path(Path.LOGO)
                .method(HttpClient.HttpMethod.PUT)
                .pathParams(userId, projectId)
                .requestBody(storage);
    }

    public CrowdinRequestBuilder<String> deleteLogo(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.LOGO)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId);
    }
}
