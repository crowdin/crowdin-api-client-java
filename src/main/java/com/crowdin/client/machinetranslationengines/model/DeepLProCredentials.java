package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

@Data
public class DeepLProCredentials implements Credentials {

    private String apiKey;
}
