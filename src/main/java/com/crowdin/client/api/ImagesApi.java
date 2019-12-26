package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.StorageDto;
import com.crowdin.common.models.Image;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class ImagesApi extends Api {
    public ImagesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Image>> uploadBackground(String projectId, StorageDto storage) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Image>>() {
        })
                .path(Path.BACKGROUND)
                .method(CrowdinHttpClient.HttpMethod.PUT)
                .pathParams(projectId)
                .requestBody(storage);
    }

    public CrowdinRequestBuilder deleteBackground(String projectId) {
        return getBuilderWithSettings()
                .path(Path.BACKGROUND)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Image>> uploadLogo(String projectId, StorageDto storage) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Image>>() {
        })
                .path(Path.LOGO)
                .method(CrowdinHttpClient.HttpMethod.PUT)
                .pathParams(projectId)
                .requestBody(storage);
    }

    public CrowdinRequestBuilder deleteLogo(String projectId) {
        return getBuilderWithSettings()
                .path(Path.LOGO)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId);
    }
}
