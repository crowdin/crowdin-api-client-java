package com.crowdin.client.translations;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;

public class TranslationsApi extends CrowdinApi {
    public TranslationsApi(Credentials credentials) {
        super(credentials);
    }

    public TranslationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }


}
