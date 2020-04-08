package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class AmazonTranslateCredentials implements Credentials {

    private String accessKey;
    private String secretKey;
}
