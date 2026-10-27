package com.crowdin.client.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        private Options trainingOptions;
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
        /**
         * @deprecated the API returns a number, use {@link #learningRateMultiplierValue} instead
         */
        @Deprecated
        @JsonIgnore
        private Long learningRateMultiplier;
        @JsonProperty("learningRateMultiplier")
        private Double learningRateMultiplierValue;
        private Long nEpochs;

        /**
         * @deprecated use {@link #learningRateMultiplierValue} instead
         */
        @Deprecated
        public void setLearningRateMultiplier(Long learningRateMultiplier) {
            this.learningRateMultiplier = learningRateMultiplier;
            this.learningRateMultiplierValue = learningRateMultiplier != null ? learningRateMultiplier.doubleValue() : null;
        }

        /**
         * @deprecated use {@link #learningRateMultiplierValue} instead
         */
        @Deprecated
        public Long getLearningRateMultiplier() {
            return this.learningRateMultiplierValue != null ? this.learningRateMultiplierValue.longValue() : null;
        }
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
