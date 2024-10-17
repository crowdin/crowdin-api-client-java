package com.crowdin.client.ai.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FineTuningJob {
    private String identifier;
    private String status;
    private Long progress;
    private JobAttribute attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;

    @Data
    public static class JobAttribute {
        private Boolean dryRun;
        private Long aiPromptId;
        private Hyperparameters hyperparameters;
        private Options trainignOptions;
        private Options validationOptions;
        private String baseModel;
        private String fineTunedModel;
        private Long trainedTokensCount;
        private String trainingDatasetUrl;
        private String validationDatasetUrl;
        private Metadata metadata;
    }

    @Data
    public static class Hyperparameters {
        private Long batchSize;
        private Long learningRateMultiplier;
        private Long nEpochs;
    }

    @Data
    public static class Options {
        private List<Long> projectIds;
        private List<Long> tmIds;
        private Date dateFrom;
        private Date dateTo;
        private Long maxFileSize;
        private Long minExamplesCount;
        private Long maxExamplesCount;
    }

    @Data
    public static class Metadata {
        private Double cost;
        private String costCurrency;
    }
}
