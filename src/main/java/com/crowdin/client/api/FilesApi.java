package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.FileEntity;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.FilePayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.RestoreFilePayload;
import com.crowdin.common.request.UpdateFilePayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class FilesApi extends Api {
    public FilesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<FileEntity>> getBranchFiles(String projectId, String branchId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<FileEntity>>() {
        })
                .path(Path.BRANCH_FILES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, branchId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<Page<FileEntity>> getDirectoryFiles(String projectId, String directoryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<FileEntity>>() {
        })
                .path(Path.DIRECTORY_FILES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, directoryId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<Page<FileEntity>> getProjectFiles(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<FileEntity>>() {
        })
                .path(Path.PROJECT_FILES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);

    }

    public CrowdinRequestBuilder<SimpleResponse<FileEntity>> createFile(String projectId, FilePayload filePayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileEntity>>() {
        })
                .path(Path.PROJECT_FILES)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(filePayload);

    }

    public CrowdinRequestBuilder<SimpleResponse<FileEntity>> getFile(String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileEntity>>() {
        })
                .path(Path.FILE)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId);

    }

    public CrowdinRequestBuilder deleteFile(String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.FILE)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, fileId);

    }

    public CrowdinRequestBuilder<SimpleResponse<FileEntity>> editFile(String projectId, String fileId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileEntity>>() {
        })
                .path(Path.FILE)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, fileId)
                .requestBody(updateOperations);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileEntity>> updateFile(String projectId, String fileId, UpdateFilePayload updateFilePayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileEntity>>() {
        })
                .path(Path.FILE)
                .method(CrowdinHttpClient.HttpMethod.PUT)
                .pathParams(projectId, fileId)
                .requestBody(updateFilePayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileEntity>> restoreFile(String projectId, String fileId, RestoreFilePayload restoreFilePayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileEntity>>() {
        })
                .path(Path.FILE)
                .method(CrowdinHttpClient.HttpMethod.PUT)
                .pathParams(projectId, fileId)
                .requestBody(restoreFilePayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportFile(String projectId, String fileId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.FILE_RAW)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId);
    }
}
