package com.crowdin.client.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class DeleteJobStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    private Error error;

    @Data
    public static class Attributes {

        private Long branchId;
        private Long directoryId;
        private Long fileId;
    }

    @Data
    public static class Error {

        private String message;
    }
}
