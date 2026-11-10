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
     * Custom fields (Enterprise only). The API returns an empty array ({@code []}) when the file has
     * no custom fields; it is deserialized as {@code null}, the same as a missing value.
     */
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, Object> fields;
}
