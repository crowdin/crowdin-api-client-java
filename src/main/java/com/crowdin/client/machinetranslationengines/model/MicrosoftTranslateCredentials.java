package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class MicrosoftTranslateCredentials implements Credentials {

    private String apiKey;
    private String model;
}
