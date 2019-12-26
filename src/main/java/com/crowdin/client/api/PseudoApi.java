package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.FileRaw;
import com.crowdin.common.models.Status;
import com.crowdin.common.request.PseudoPayload;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class PseudoApi extends Api {

    public PseudoApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getPseudoExportStatus(String glossaryId, String jobIdentifier) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PSEUDO_EXPORT_JOBS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportPseudo(String glossaryId, PseudoPayload pseudo) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Status>>() {
        })
                .path(Path.PSEUDO_EXPORT)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(glossaryId)
                .requestBody(pseudo);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getPseudoRaw(String glossaryId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<FileRaw>>() {
        })
                .path(Path.PSEUDO_RAW)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId);
    }
}
