package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class WatsonTranslateCredentials implements Credentials {

    private String apiKey;
    private String endpoint;
}
