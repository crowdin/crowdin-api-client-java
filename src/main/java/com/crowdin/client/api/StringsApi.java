package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.CrowdinString;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.CrowdinStringPayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class StringsApi extends Api {

    public StringsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<CrowdinString>> getFileStrings(String userId, String projectId, String fileId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<CrowdinString>>() {
        })
                .path(Path.FILE_SRTINGS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> createFileString(String userId, String projectId, String fileId, CrowdinStringPayload string) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.FILE_SRTINGS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId, fileId)
                .requestBody(string);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> getFileString(String userId, String projectId, String fileId, String stringId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.FILE_STRING)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder<String> deleteFileString(String userId, String projectId, String fileId, String stringId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.FILE_STRING)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder<String> patchFileString(String userId, String projectId, String fileId, String stringId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.FILE_STRING)
                .method(HttpClient.HttpMethod.PATCH)
                .requestBody(updateOperations)
                .pathParams(userId, projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder<Page<CrowdinString>> getProjectStrings(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRINGS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> createProjectString(String userId, String projectId, CrowdinStringPayload string) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(string);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> getProjectString(String userId, String projectId, String stringId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, stringId);
    }

    public CrowdinRequestBuilder<String> deleteProjectString(String userId, String projectId, String stringId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, stringId);
    }

    public CrowdinRequestBuilder<String> patchProjectString(String userId, String projectId, String stringId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(HttpClient.HttpMethod.PATCH)
                .requestBody(updateOperations)
                .pathParams(userId, projectId, stringId);
    }
}
