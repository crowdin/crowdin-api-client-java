package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
public class FileInfo {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;
    private String name;
    private String title;
    private String context;
    private String type;
    private String path;
    private String status;
    /**
     * Custom fields (Enterprise only)
     */
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, Object> fields;
}
