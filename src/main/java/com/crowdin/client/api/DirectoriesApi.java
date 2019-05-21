package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Directory;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.DirectoryPayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class DirectoriesApi extends Api {

    public DirectoriesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Directory>> getBranchDirectories(String projectId, String branchId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.BRANCH_DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, branchId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<Directory>> getSubDirectories(String projectId, String directoryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.SUB_DIRECTORIES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, directoryId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<Directory>> getProjectDirectories(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Directory>>() {
        })
                .path(Path.PROJECT_DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> createDirectory(String projectId, DirectoryPayload directory) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.PROJECT_DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(directory);
    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> getDirectory(String projectId, String directoryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, directoryId);
    }

    public CrowdinRequestBuilder deleteDirectory(String projectId, String directoryId) {
        return getBuilderWithSettings()
                .path(Path.DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, directoryId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Directory>> updateDirectory(String projectId, String directoryId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Directory>>() {
        })
                .path(Path.DIRECTORY)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, directoryId)
                .requestBody(updateOperations);
    }
}
