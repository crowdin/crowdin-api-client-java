package com.crowdin.client.translationmemory.model;

import lombok.Data;

@Data
public class AddTranslationMemoryRequest {

    private String name;
    private Long groupId;
}
