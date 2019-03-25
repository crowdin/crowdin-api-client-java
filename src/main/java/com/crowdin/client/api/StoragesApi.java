package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.FileDto;
import com.crowdin.common.dto.IdDto;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class StoragesApi extends Api {

    public StoragesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<IdDto>> getStorage(String userId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<IdDto>>() {
        })
                .path(Path.STORAGES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<IdDto>> uploadFile(String userId, FileDto string) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<IdDto>>() {
        })
                .path(Path.FILE_SRTINGS)
                .method(HttpClient.HttpMethod.POST)
                .requestBody(string)
                .pathParams(userId);
    }

    public CrowdinRequestBuilder<SimpleResponse<IdDto>> getStorage(String userId, String storageId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<IdDto>>() {
        })
                .path(Path.STORAGE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, storageId);
    }

    public CrowdinRequestBuilder<String> deleteStorage(String userId, String storageId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.STORAGE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, storageId);
    }
}
