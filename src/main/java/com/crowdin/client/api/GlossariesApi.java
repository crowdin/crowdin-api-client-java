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
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class GlossariesApi extends Api {
    public GlossariesApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Glossary>>() {
        })
                .path(Path.GLOSSARIES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams()
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> getGlossary(String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GLOSSARY)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId);
    }

    public CrowdinRequestBuilder deleteGlossary(String glossaryId) {
        return getBuilderWithSettings()
                .path(Path.GLOSSARY)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(glossaryId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> updateGlossary(String glossaryId, List<PatchOperation> updateOperation) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GLOSSARY)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(glossaryId)
                .requestBody(updateOperation);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportGlossary(String glossaryId, FormatDto format) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARY_EXPORT)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(glossaryId)
                .requestBody(format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importGlossary(String glossaryId, GlossaryPayload glossary) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARY_IMPORT)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(glossaryId)
                .requestBody(glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getGlossaryRaw(String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.GLOSSARY_RAW)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId);
    }

    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(String groupId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Glossary>>() {
        })
                .path(Path.GROUP_GLOSSARIES)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(groupId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> createGroupGlossary(String groupId, NameDto name) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Glossary>>() {
        })
                .path(Path.GROUP_GLOSSARIES)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(groupId)
                .requestBody(name);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportProjectGlossary(String projectId, FormatDto format) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_EXPORT)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importProjectGlossary(String projectId, GlossaryPayload glossary) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_IMPORT)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId)
                .requestBody(glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getProjectGlossaryRaw(String projectId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_RAW)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryExportStatus(String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARIES_EXPORT_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryImportStatus(String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.GLOSSARIES_IMPORT_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryExportStatus(String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_EXPORT_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryImportStatus(String projectId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PROJECT_GLOSSARIES_IMPORT_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, jobIdentifier);
    }
}
