package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Issue;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.IssuePayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class IssuesApi extends Api {

    public IssuesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Issue>> getIssues(String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Issue>>() {
        })
                .path(Path.ISSUES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Issue>> createIssue(String projectId, IssuePayload issue) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Issue>>() {
        })
                .path(Path.ISSUES)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(issue);
    }

    public CrowdinRequestBuilder<SimpleResponse<Issue>> getIssue(String projectId, String issueId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Issue>>() {
        })
                .path(Path.ISSUE)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, issueId);
    }

    public CrowdinRequestBuilder deleteIssue(String projectId, String issueId) {
        return getBuilderWithSettings()
                .path(Path.ISSUE)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId, issueId);
    }

    public CrowdinRequestBuilder<String> updateIssue(String projectId, String issueId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.ISSUE)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId, issueId)
                .requestBody(updateOperations);
    }
}
