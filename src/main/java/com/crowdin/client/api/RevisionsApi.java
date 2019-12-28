package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Revision;
import com.crowdin.common.request.RevisionPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class RevisionsApi extends Api {

    public RevisionsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Revision>> getRevisions(String projectId, String fileId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Revision>>() {
        })
                .path(Path.REVISIONS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Revision>> getRevision(String projectId, String fileId, String revisionId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Revision>>() {
        })
                .path(Path.REVISION)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(projectId, fileId, revisionId);
    }
}
