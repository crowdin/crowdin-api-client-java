package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Issue;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Project;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.ProjectPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ProjectsApi extends Api {

    public ProjectsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Project>> getRootGroupProjects(String userId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Project>>() {
        })
                .path(Path.PROJECTS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Project>> createRootGroupProject(String userId, ProjectPayload projectPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Project>>() {
        })
                .path(Path.PROJECTS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId)
                .requestBody(projectPayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<Project>> getProject(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Project>>() {
        })
                .path(Path.PROJECT)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId);
    }

    public CrowdinRequestBuilder<String> deleteProject(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.PROJECT)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Project>> updateProject(String userId, String projectId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Project>>() {
        })
                .path(Path.ISSUE)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, projectId)
                .requestBody(updateOperations);
    }

    public CrowdinRequestBuilder<Page<Project>> getGroupProjects(String userId, String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Project>>() {
        })
                .path(Path.GROUP_PROJECTS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Issue>> createGroupProject(String userId, String groupId, ProjectPayload projectPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Issue>>() {
        })
                .path(Path.GROUP_PROJECTS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, groupId)
                .requestBody(projectPayload);
    }
}
