package com.crowdin.client.machinetranslationengines.model;

import lombok.Data;

import java.util.List;

@Data
public class AddMachineTranslationRequest {

    private String name;
    private Type type;
    private List<Credentials> credentials;
    private Long groupId;

}
