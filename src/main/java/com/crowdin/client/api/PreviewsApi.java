package com.crowdin.client.api;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.Path;
import com.crowdin.common.Settings;
import com.crowdin.common.models.DataSources;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.CrowdinHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class PreviewsApi extends Api {

    public PreviewsApi(Settings settings) {
        super(settings);
    }

    public CrowdinRequestBuilder<SimpleResponse<DataSources>> preTranslateProjectFiles(String storagetId) {
        return getBuilderWithSettings(new TypeReference<SimpleResponse<DataSources>>() {
        })
                .path(Path.PREVIEW)
                .method(CrowdinHttpClient.HttpMethod.GET)
                .pathParams();
    }
}
