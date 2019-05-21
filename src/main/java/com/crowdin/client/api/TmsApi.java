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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TmsApi extends Api {

    public TmsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getTmsByGroupUploadingStatus(String groupId, String tmsId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TMS_GROUP_UPLOAD_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId, tmsId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getTmsByProjectUploadingStatus(String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TMS_PROJECT_UPLOAD_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, jobIdentifier);
    }

    public CrowdinRequestBuilder<Page<TM>> getTms(String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_GROUPS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<Page<TM>> createGroupTm(String groupId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_GROUPS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .requestBody(name)
                .pathParams(groupId);
    }

    public CrowdinRequestBuilder<SimpleResponse<TM>> getTmByGroup(String groupId, String nameId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<TM>>() {
        })
                .path(Path.TM)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId, nameId);
    }

    public CrowdinRequestBuilder deleteTM(String groupId, String tmId) {
        return getBuilderWithSettings()
                .path(Path.TM)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<String>> updateTM(String groupId, String tmId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<String>>() {
        })
                .path(Path.TM)
                .requestBody(updateOperation)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportGroupTM(String groupId, String tmId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_GROUP_EXPORT)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> startExportGroupTM(String groupId, String tmId, TMStartUploadPayload startUploadPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_GROUP_EXPORT)
                .requestBody(startUploadPayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(groupId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadTmGroupRaw(String groupId, String tmId, TMPayload tmPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TM_GROUP_UPLOAD_RAW)
                .requestBody(tmPayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(groupId, tmId);
    }

    public CrowdinRequestBuilder<Page<TM>> createProjectTm(String projectId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<Page<TM>>() {
        })
                .path(Path.TMS_PROJECT)
                .requestBody(name)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId);
    }


    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> exportProjectTM(String projectId, String tmId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_PROJECT_EXPORT)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> startExportProjectTM(String projectId, String tmId, TMStartUploadPayload startUploadPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.TM_PROJECT_EXPORT)
                .requestBody(startUploadPayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId, tmId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> uploadProjectTmRaw(String projectId, String tmId, TMPayload tmPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.TM_PROJECT_UPLOAD_RAW)
                .requestBody(tmPayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId, tmId);
    }
}
