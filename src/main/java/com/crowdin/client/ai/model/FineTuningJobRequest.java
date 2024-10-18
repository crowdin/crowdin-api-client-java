package com.crowdin.client.ai.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FineTuningJobRequest {
    private Boolean dryRun;
    private Hyperparameters hyperparameters;
    private Options trainingOptions;
    private Options validationOptions;

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
}
