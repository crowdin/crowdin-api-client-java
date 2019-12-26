package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Term;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.request.TermPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TermsApi extends Api {

    public TermsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Term>> getTerms(String glossaryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Term>>() {
        })
                .path(Path.TERMS)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> createTerm(String glossaryId, TermPayload termPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.TERMS)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(glossaryId)
                .requestBody(termPayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> getTerm(String glossaryId, String termId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.TERM)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams(glossaryId, termId);
    }

    public CrowdinRequestBuilder deleteTerm(String glossaryId, String termId) {
        return getBuilderWithSettings()
                .path(Path.TERM)
                .method(CrowdinHttpClient.HttpMethod.DELETE)
                .pathParams(glossaryId, termId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> updateTerm(String glossaryId, String termId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.TERM)
                .method(CrowdinHttpClient.HttpMethod.PATCH)
                .pathParams(glossaryId, termId)
                .requestBody(updateOperations);
    }
}
