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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class GroupsApi extends Api {
    public GroupsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Group>> getSubGroups(String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Group>>() {
        })
                .path(Path.SUB_GROUPS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> createSubGroup(String groupId, GroupDto group) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.SUB_GROUPS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(groupId)
                .requestBody(group);
    }

    public CrowdinRequestBuilder<Page<Group>> getAllGroups(Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Group>>() {
        })
                .path(Path.GROUPS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams()
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> createRootGroup(GroupDto group) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUPS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams()
                .requestBody(group);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> getGroup(String groupId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUP)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId);
    }

    public CrowdinRequestBuilder deleteGroup(String groupId) {
        return getBuilderWithSettings()
                .path(Path.GROUP)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(groupId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Group>> updateGroup(String groupId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Group>>() {
        })
                .path(Path.GROUP)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(groupId)
                .requestBody(updateOperations);
    }
}
