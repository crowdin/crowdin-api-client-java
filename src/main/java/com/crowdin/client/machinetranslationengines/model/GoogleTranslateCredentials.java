package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class GoogleTranslateCredentials implements Credentials {

    private String apiKey;
}
