package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Branch;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.BranchPayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class BranchesApi extends Api {

    public BranchesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Branch>> getBranches(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Branch>>() {
        })
                .path(Path.BRANCHES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Branch>> createBranch(String projectId, BranchPayload branch) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Branch>>() {
        })
                .path(Path.BRANCHES)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(branch);
    }

    public CrowdinRequestBuilder<Page<Branch>> getBranch(String projectId, String branchId) {
        return getBuilderWithSettings(new TypeReference<Page<Branch>>() {
        })
                .path(Path.BRANCH)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, branchId);
    }

    public CrowdinRequestBuilder deleteBranch(String projectId, String branchId) {
        return getBuilderWithSettings()
                .path(Path.BRANCH)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, branchId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Branch>> updateBranch(String projectId, String branchId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Branch>>() {
        })
                .path(Path.BRANCH)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, branchId)
                .requestBody(updateOperations);
    }
}
