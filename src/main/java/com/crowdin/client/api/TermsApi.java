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
import com.crowdin.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TermsApi extends Api {

    public TermsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Term>> getTerms(String userId, String glossaryId, Pageable pageable) {
        return getBuilderWithSettings(new TypeReference<Page<Term>>() {
        })
                .path(Path.TERMS)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId)
                .pageable(pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> createTerm(String userId, String glossaryId, TermPayload termPayload) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.TERMS)
                .method(HttpClient.HttpMethod.POST)
                .pathParams(userId, glossaryId)
                .requestBody(termPayload);
    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> getTerm(String userId, String glossaryId, String termId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.TERM)
                .method(HttpClient.HttpMethod.GET)
                .pathParams(userId, glossaryId, termId);
    }

    public CrowdinRequestBuilder<String> deleteTerm(String userId, String glossaryId, String termId) {
        return getBuilderWithSettings(new TypeReference<String>() {
        })
                .path(Path.REFERENCE)
                .method(HttpClient.HttpMethod.DELETE)
                .pathParams(userId, glossaryId, termId);

    }

    public CrowdinRequestBuilder<SimpleResponse<Term>> updateProject(String userId, String glossaryId, String termId, List<PatchOperation> updateOperations) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<Term>>() {
        })
                .path(Path.REFERENCE)
                .method(HttpClient.HttpMethod.PATCH)
                .pathParams(userId, glossaryId, termId)
                .requestBody(updateOperations);
    }
}
