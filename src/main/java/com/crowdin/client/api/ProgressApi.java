package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Progress;
import com.crowdin.common.response.Page;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class ProgressApi extends Api {

    public ProgressApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Progress>> getBranchesProgress(String userId, String projectId, String branchId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Progress>>() {
        })
                .path(Path.BRANCHES_PROGRESS)
                .method(HttpClient.HttpMethod.GET)
                .pageable(pageable)
                .pathParams(userId, projectId, branchId);
    }

    public CrowdinRequestBuilder<Page<Progress>> getDirectoriesProgress(String userId, String projectId, String directoryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Progress>>() {
        })
                .path(Path.DIRECTORIES_PROGRESS)
                .method(HttpClient.HttpMethod.GET)
                .pageable(pageable)
                .pathParams(userId, projectId, directoryId);
    }

    public CrowdinRequestBuilder<Page<Progress>> getFilesProgress(String userId, String projectId, String fileId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Progress>>() {
        })
                .path(Path.FILES_PROGRESS)
                .method(HttpClient.HttpMethod.GET)
                .pageable(pageable)
                .pathParams(userId, projectId, fileId);
    }

    public CrowdinRequestBuilder<Page<Progress>> getProjectProgress(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Progress>>() {
        })
                .path(Path.PROJECTS_PROGRESS)
                .method(HttpClient.HttpMethod.GET)
                .pageable(pageable)
                .pathParams(userId, projectId);
    }
}
