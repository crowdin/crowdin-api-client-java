package com.crowdin.client.placeholders.model;

import lombok.Data;

import java.util.List;

@Data
public class AddProjectPlaceholderRequest {

    private Long customPlaceholderId;
    private ProjectPlaceholderType type;
    private Integer index;
    private Boolean isBlocking;
    private List<String> formats;
}
