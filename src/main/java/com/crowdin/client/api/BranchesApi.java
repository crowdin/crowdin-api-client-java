package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class BranchesApi extends Api {

    public BranchesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<com.crowdin.common.models.Branch>> getBranches(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<com.crowdin.common.models.Branch>>() {
        })
                .path(Path.BRANCHES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<com.crowdin.common.models.Branch>> createBranch(String userId, String projectId, com.crowdin.common.models.Branch branch) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<com.crowdin.common.models.Branch>>() {
        })
                .path(Path.BRANCHES)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(branch);
    }

    public CrowdinRequestBuilder<Page<com.crowdin.common.models.Branch>> getBranch(String userId, String projectId, String branchId) {
        return getBuilderWithSettings(new TypeReference<Page<com.crowdin.common.models.Branch>>() {
        })
                .path(Path.BRANCH)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, branchId);
    }

    public CrowdinRequestBuilder<String> deleteBranch(String userId, String projectId, String branchId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.BRANCH)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, branchId);
    }

    public CrowdinRequestBuilder<SimpleResponse<com.crowdin.common.models.Branch>> updateBranch(String userId, String projectId, String branchId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<com.crowdin.common.models.Branch>>() {
        })
                .path(Path.BRANCH)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, branchId)
                .requestBody(updateOperations);
    }
}
