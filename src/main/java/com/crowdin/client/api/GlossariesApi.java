package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.FormatDto;
import com.crowdin.common.dto.NameDto;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Glossary;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Status;
import com.crowdin.common.request.GlossaryPayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class GlossariesApi extends Api {
    public GlossariesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(String userId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Glossary>>() {
        })
                .path(Path.GLOSSARIES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> getGlossary(String userId, String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GLOSSARY)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> deleteGlossary(String userId, String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GLOSSARY)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, glossaryId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> updateGlossary(String userId, String glossaryId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GLOSSARY)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, glossaryId)
                .requestBody(updateOperation);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportGlossary(String userId, String glossaryId, FormatDto format) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARY_EXPORT)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, glossaryId)
                .requestBody(format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importGlossary(String userId, String glossaryId, GlossaryPayload glossary) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARY_IMPORT)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, glossaryId)
                .requestBody(glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getGlossaryRaw(String userId, String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.GLOSSARY_RAW)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId);
    }

    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(String userId, String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Glossary>>() {
        })
                .path(Path.GROUP_GLOSSARIES)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> createGroupGlossary(String userId, String groupId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GROUP_GLOSSARIES)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, groupId)
                .requestBody(name);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportProjectGlossary(String userId, String projectId, FormatDto format) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_EXPORT)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importProjectGlossary(String userId, String projectId, GlossaryPayload glossary) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_IMPORT)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, projectId)
                .requestBody(glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getProjectGlossaryRaw(String userId, String projectId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_RAW)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryExportStatus(String userId, String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARIES_EXPORT_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryImportStatus(String userId, String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARIES_IMPORT_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryExportStatus(String userId, String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_EXPORT_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryImportStatus(String userId, String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_IMPORT_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, projectId, jobIdentifier);
    }
}
