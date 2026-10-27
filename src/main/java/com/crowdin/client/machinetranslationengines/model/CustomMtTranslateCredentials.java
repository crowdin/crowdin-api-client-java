package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class CustomMtTranslateCredentials implements Credentials {

    /**
     * Base URL
     */
    private String url;
}
