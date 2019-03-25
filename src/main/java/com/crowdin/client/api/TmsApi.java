package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.NameDto;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Status;
import com.crowdin.common.models.TM;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.TMPayload;
import com.crowdin.common.request.TMStartUploadPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TmsApi extends Api {

    public TmsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getTmsByGroupUploadingStatus(String userId, String groupId, String tmsId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TMS_GROUP_UPLOAD_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId, tmsId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getTmsByProjectUploadingStatus(String userId, String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TMS_PROJECT_UPLOAD_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, jobIdentifier);
    }

    public CrowdinRequestBuilder<Page<TM>> getTms(String userId, String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_GROUPS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<TM>> createGroupTm(String userId, String groupId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_GROUPS)
                .method(HttpClient.HttpMethod.POST)
                .requestBody(name)
                .pathParams(userId, groupId);
    }

    public CrowdinRequestBuilder<SimpleResponse<TM>> getTmByGroup(String userId, String groupId, String nameId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<TM>>() {
        })
                .path(Path.TM)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId, nameId);
    }

    public CrowdinRequestBuilder<SimpleResponse<String>> deleteTM(String userId, String groupId, String tmId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<String>>() {
        })
                .path(Path.TM)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<String>> updateTM(String userId, String groupId, String tmId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<String>>() {
        })
                .path(Path.TM)
                .requestBody(updateOperation)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportGroupTM(String userId, String groupId, String tmId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_GROUP_EXPORT)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> startExportGroupTM(String userId, String groupId, String tmId, TMStartUploadPayload startUploadPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_GROUP_EXPORT)
                .requestBody(startUploadPayload)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadTmGroupRaw(String userId, String groupId, String tmId, TMPayload tmPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TM_GROUP_UPLOAD_RAW)
                .requestBody(tmPayload)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, groupId, tmId);
    }

    public CrowdinRequestBuilder<Page<TM>> createProjectTm(String userId, String projectId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_PROJECT)
                .requestBody(name)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId);
    }


    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportProjectTM(String userId, String projectId, String tmId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_PROJECT_EXPORT)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> startExportProjectTM(String userId, String projectId, String tmId, TMStartUploadPayload startUploadPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_PROJECT_EXPORT)
                .requestBody(startUploadPayload)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadProjectTmRaw(String userId, String projectId, String tmId, TMPayload tmPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TM_PROJECT_UPLOAD_RAW)
                .requestBody(tmPayload)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId, tmId);
    }
}
