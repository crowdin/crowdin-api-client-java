package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.GroupDto;
import com.crowdin.common.models.Group;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class GroupsApi extends Api {
    public GroupsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Group>> getSubGroups(String userId, String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Group>>() {
        })
                .path(Path.SUB_GROUPS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> createSubGroup(String userId, String groupId, GroupDto group) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.SUB_GROUPS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, groupId)
                .requestBody(group);
    }

    public CrowdinRequestBuilder<Page<Group>> getAllGroups(String userId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Group>>() {
        })
                .path(Path.GROUPS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> createRootGroup(String userId, GroupDto group) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUPS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId)
                .requestBody(group);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> getGroup(String userId, String groupId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUP)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> deleteGroup(String userId, String groupId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUP)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, groupId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> updateGroup(String userId, String groupId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUP)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, groupId)
                .requestBody(updateOperations);
    }
}
