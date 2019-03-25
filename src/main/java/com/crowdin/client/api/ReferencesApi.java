package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Reference;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.StorageReference;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ReferencesApi extends Api {

    public ReferencesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Reference>> getReferences(String userId, String projectId, String fileId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Reference>>() {
        })
                .path(Path.REFERENCES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Reference>> createReferences(String userId, String projectId, String fileId, StorageReference storageReference) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Reference>>() {
        })
                .path(Path.REFERENCES)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId, fileId)
                .requestBody(storageReference);
    }

    public CrowdinRequestBuilder<SimpleResponse<Reference>> getReferences(String userId, String projectId, String fileId, String referenceId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Reference>>() {
        })
                .path(Path.REFERENCE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId, projectId, referenceId);
    }

    public CrowdinRequestBuilder<String> deleteProject(String userId, String projectId, String fileId, String referenceId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.REFERENCE)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, fileId, projectId, referenceId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Reference>> updateProject(String userId, String projectId, String fileId, String referenceId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Reference>>() {
        })
                .path(Path.REFERENCE)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, fileId, projectId, referenceId)
                .requestBody(updateOperations);
    }
}
