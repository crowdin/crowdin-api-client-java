package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Format;
import lombok.Data;

import java.util.Date;

@Data
public class GlossaryExportStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;
    private String eta;

    @Data
    public static class Attributes {

        private Format format;
        private Long organizationId;
        private Long glossaryId;
    }
}
