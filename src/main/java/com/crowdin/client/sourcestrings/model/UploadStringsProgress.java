package com.crowdin.client.sourcestrings.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UploadStringsProgress {

    private String identifier;
    private String status;
    private int progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    private String eta;

    @Data
    public static class Attributes {
        private Long branchId;
        private Long storageId;
        private String fileType;
        private Integer parserVersion;
        private List<Long> labelIds;
        @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
        private ImportOptions importOptions;
    }
}
