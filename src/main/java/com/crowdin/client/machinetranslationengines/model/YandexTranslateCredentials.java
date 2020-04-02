package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class YandexTranslateCredentials implements Credentials {

    private String apiKey;
}
