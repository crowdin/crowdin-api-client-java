package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Directory;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class DirectoriesApi extends Api {

    public DirectoriesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Directory>> getBranchDirectories(String userId, String projectId, String branchId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.BRANCH_DIRECTORY)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, branchId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<Directory>> getSubDirectories(String userId, String projectId, String directoryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.SUB_DIRECTORIES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, directoryId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<Directory>> getProjectDirectories(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.PROJECT_DIRECTORY)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> createDirectory(String userId, String projectId, Directory directory) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.PROJECT_DIRECTORY)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(directory);
    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> getDirectory(String userId, String projectId, String directoryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.DIRECTORY)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, directoryId);
    }

    public CrowdinRequestBuilder<String> deleteDirectory(String userId, String projectId, String directoryId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.DIRECTORY)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, directoryId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> updateDirectory(String userId, String projectId, String directoryId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.DIRECTORY)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, directoryId)
                .requestBody(updateOperations);
    }
}
