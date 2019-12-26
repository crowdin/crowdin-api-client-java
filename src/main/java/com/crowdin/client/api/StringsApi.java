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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class StringsApi extends Api {

    public StringsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<CrowdinString>> getFileStrings(String projectId, String fileId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<CrowdinString>>() {
        })
                .path(Path.FILE_SRTINGS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> createFileString(String projectId, String fileId, CrowdinStringPayload string) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.FILE_SRTINGS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId, fileId)
                .requestBody(string);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> getFileString(String projectId, String fileId, String stringId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.FILE_STRING)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder deleteFileString(String projectId, String fileId, String stringId) {
        return getBuilderWithSettings()
                .path(Path.FILE_STRING)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder<String> patchFileString(String projectId, String fileId, String stringId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.FILE_STRING)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .requestBody(updateOperations)
                .pathParams(projectId, fileId, stringId);
    }

    public CrowdinRequestBuilder<Page<CrowdinString>> getProjectStrings(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRINGS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> createProjectString(String projectId, CrowdinStringPayload string) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(string);
    }

    public CrowdinRequestBuilder<SimpleResponse<CrowdinString>> getProjectString(String projectId, String stringId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<CrowdinString>>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, stringId);
    }

    public CrowdinRequestBuilder deleteProjectString(String projectId, String stringId) {
        return getBuilderWithSettings()
                .path(Path.PROJECTS_STRING)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, stringId);
    }

    public CrowdinRequestBuilder<String> patchProjectString(String projectId, String stringId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.PROJECTS_STRING)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .requestBody(updateOperations)
                .pathParams(projectId, stringId);
    }
}
