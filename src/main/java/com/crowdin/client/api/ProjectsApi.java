package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Project;
import com.crowdin.common.models.ProjectSettings;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.ProjectPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ProjectsApi extends Api {

    public ProjectsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Project>> getRootGroupProjects(Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Project>>() {
        })
                .path(Path.PROJECTS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams()
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Project>> createRootGroupProject(ProjectPayload projectPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Project>>() {
        })
                .path(Path.PROJECTS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams()
                .requestBody(projectPayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<ProjectSettings>> getProject(String projectId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<ProjectSettings>>() {
        })
                .path(Path.PROJECT)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId);
    }

    public CrowdinRequestBuilder deleteProject(String projectId) {
        return getBuilderWithSettings()
                .path(Path.PROJECT)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<ProjectSettings>> updateProject(String projectId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<ProjectSettings>>() {
        })
                .path(Path.PROJECT)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(projectId)
                .requestBody(updateOperations);
    }

    public CrowdinRequestBuilder<Page<Project>> getGroupProjects(String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Project>>() {
        })
                .path(Path.GROUP_PROJECTS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Project>> createGroupProject(String groupId, ProjectPayload projectPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Project>>() {
        })
                .path(Path.GROUP_PROJECTS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(groupId)
                .requestBody(projectPayload);
    }
}
