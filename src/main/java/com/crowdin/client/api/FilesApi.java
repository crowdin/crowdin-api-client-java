package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.File;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.FilePayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class FilesApi extends Api {
    public FilesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<File>> getBranchFiles(String userId, String projectId, String branchId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<File>>() {
        })
                .path(Path.BRANCH_FILES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, branchId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<Page<File>> getDirectoryFiles(String userId, String projectId, String directoryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<File>>() {
        })
                .path(Path.DIRECTORY_FILES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, directoryId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<Page<File>> getProjectFiles(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<File>>() {
        })
                .path(Path.PROJECT_FILES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<SimpleResponse<File>> createFile(String userId, String projectId, FilePayload filePayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<File>>() {
        })
                .path(Path.PROJECT_FILES)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(filePayload);

    }

    public CrowdinRequestBuilder<SimpleResponse<File>> getFile(String userId, String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<File>>() {
        })
                .path(Path.FILE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId);

    }

    public CrowdinRequestBuilder<String> deleteFile(String userId, String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.FILE)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, fileId);

    }

    public CrowdinRequestBuilder<SimpleResponse<File>> updateFile(String userId, String projectId, String fileId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<File>>() {
        })
                .path(Path.FILE)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, fileId)
                .requestBody(updateOperations);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportFile(String userId, String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.FILE_RAW)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId);
    }

}
