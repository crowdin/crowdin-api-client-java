package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Status;
import com.crowdin.common.request.PseudoPayload;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class PseudoApi extends Api {

    public PseudoApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getPseudoExportStatus(String userId, String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PSEUDO_EXPORT_JOBS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportPseudo(String userId, String glossaryId, PseudoPayload pseudo) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PSEUDO_EXPORT)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, glossaryId)
                .requestBody(pseudo);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getPseudoRaw(String userId, String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.PSEUDO_RAW)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId);
    }
}
