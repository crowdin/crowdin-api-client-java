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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ScreenshotsApi extends Api {

    public ScreenshotsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Screenshot>> getScreenshots(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Screenshot>>() {
        })
                .path(Path.SCREENSHOTS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> createScreenshot(String projectId, StorageReference storageReference) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOTS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(storageReference);
    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> getScreenshot(String projectId, String fileId, String screenshotId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId, projectId, screenshotId);
    }


    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> replaceScreenshot(String projectId, String fileId, String screenshotId, StorageReference storageReference) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(CrowdinHttpClient.HttpMethod.PUT)
                .requestBody(storageReference)
                .pathParams(projectId, fileId, projectId, screenshotId);
    }

    public CrowdinRequestBuilder deleteProject(String projectId, String fileId, String screenshotId) {
        return getBuilderWithSettings()
                .path(Path.SCREENSHOT)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, fileId, projectId, screenshotId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Screenshot>> updateProject(String projectId, String fileId, String referenceId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Screenshot>>() {
        })
                .path(Path.SCREENSHOT)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, fileId, projectId, referenceId)
                .requestBody(updateOperations);
    }
}
