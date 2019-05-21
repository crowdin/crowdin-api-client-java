package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.IdDto;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;

public class StorageApi extends Api {

    public StorageApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<IdDto>> getStorage(Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<IdDto>>() {
        })
                .path(Path.STORAGES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams()
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<IdDto>> uploadFile(File file) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<IdDto>>() {
        })
                .path(Path.STORAGES)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .requestBody(file)
                .pathParams();
    }

    public CrowdinRequestBuilder<SimpleResponse<IdDto>> getStorage(String storageId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<IdDto>>() {
        })
                .path(Path.STORAGE)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(storageId);
    }

    public CrowdinRequestBuilder deleteStorage(String storageId) {
        return getBuilderWithSettings()
                .path(Path.STORAGE)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(storageId);
    }
}
