package com.crowdin.client.ai.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FineTuningDatasetResponse {
    private FineTuningDatasetData data;

    @Data
    public static class FineTuningDatasetData {
        private String identifier;
        private String status;
        private Long progress;
        private DatasetAttributes attributes;
        private Date createdAt;
        private Date updatedAt;
        private Date startedAt;
    }

    @Data
    public static class DatasetAttributes {
        private List<Long> projectIds;
        private List<Long> tmIds;
        private String purpose;
        private Date dateFrom;
        private Date dateTo;
        private Long maxFileSize;
        private Long minExamplesCount;
        private Long maxExamplesCount;
    }
}
