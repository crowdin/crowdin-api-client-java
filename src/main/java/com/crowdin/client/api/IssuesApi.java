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
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class IssuesApi extends Api {

    public IssuesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Issue>> getIssues(String userId, String projectId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Issue>>() {
        })
                .path(Path.ISSUES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Issue>> createIssue(String userId, String projectId, IssuePayload issue) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Issue>>() {
        })
                .path(Path.ISSUES)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(issue);
    }

    public CrowdinRequestBuilder<SimpleResponse<Issue>> getIssue(String userId, String projectId, String issueId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Issue>>() {
        })
                .path(Path.ISSUE)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, issueId);
    }

    public CrowdinRequestBuilder<String> deleteIssue(String userId, String projectId, String issueId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.ISSUE)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId, issueId);
    }

    public CrowdinRequestBuilder<String> updateIssue(String userId, String projectId, String issueId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.ISSUE)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId, issueId)
                .requestBody(updateOperations);
    }
}
