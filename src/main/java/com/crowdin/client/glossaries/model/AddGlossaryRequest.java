package com.crowdin.client.glossaries.model;

import lombok.Data;

@Data
public class AddGlossaryRequest {

    private String name;
    private Long groupId;
}
