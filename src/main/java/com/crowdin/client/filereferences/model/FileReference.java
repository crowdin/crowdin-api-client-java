package com.crowdin.client.filereferences.model;

import lombok.Data;

@Data
public class FileReference {
    private Long id;
    private String name;
    private String type;
    private Long projectId;
}
