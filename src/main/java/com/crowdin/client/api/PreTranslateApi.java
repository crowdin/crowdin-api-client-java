package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Language;
import com.crowdin.common.request.PreTranslatePayload;
import com.crowdin.common.response.Page;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class PreTranslateApi extends Api {

    public PreTranslateApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<Page<Language>> preTranslateProjectFiles(String projectId, PreTranslatePayload preTranslatePayload) {
        return getBuilderWithSettings(new TypeReference<Page<Language>>() {
        })
                .path(Path.PRE_TRANSLATE)
                .requestBody(preTranslatePayload)
                .method(CrowdinHttpClient.HttpMethod.POST)
                .pathParams(projectId);
    }
}
