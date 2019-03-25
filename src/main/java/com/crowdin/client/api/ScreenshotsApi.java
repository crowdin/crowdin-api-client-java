package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Screenshot;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.StorageReference;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ScreenshotsApi extends Api {

    public ScreenshotsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Screenshot>> getScreenshots(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Screenshot>>() {
        })
                .path(Path.SCREENSHOTS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> createScreenshot(String userId, String projectId, StorageReference storageReference) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOTS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(storageReference);
    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> getScreenshot(String userId, String projectId, String fileId, String screenshotId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, fileId, projectId, screenshotId);
    }


    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> replaceScreenshot(String userId, String projectId, String fileId, String screenshotId, StorageReference storageReference) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(HttpClient.HttpMethod.PUT)
                .requestBody(storageReference)
                .pathParams(userId, projectId, fileId, projectId, screenshotId);
    }

    public CrowdinRequestBuilder<String> deleteProject(String userId, String projectId, String fileId, String screenshotId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.SCREENSHOT)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, fileId, projectId, screenshotId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> updateProject(String userId, String projectId, String fileId, String referenceId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, fileId, projectId, referenceId)
                .requestBody(updateOperations);
    }
}
