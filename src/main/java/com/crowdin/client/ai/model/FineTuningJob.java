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
         * @param learningRateMultiplier learning rate multiplier as a whole number
         * @deprecated use {@code setLearningRateMultiplierValue(Double)} ({@link #learningRateMultiplierValue}) instead
         */
        @Deprecated
        public void setLearningRateMultiplier(Long learningRateMultiplier) {
            this.learningRateMultiplier = learningRateMultiplier;
            this.learningRateMultiplierValue = learningRateMultiplier != null ? learningRateMultiplier.doubleValue() : null;
        }

        /**
         * Legacy whole-number view of {@link #learningRateMultiplierValue}. A finite value within the {@link Long}
         * range is truncated towards zero (e.g. {@code 1.8} &rarr; {@code 1}, {@code -0.5} &rarr; {@code 0}); a value
         * outside the {@link Long} range, {@code NaN} or an infinite value is returned as {@code null}.
         *
         * @return learning rate multiplier truncated to a whole number, or {@code null}
         * @deprecated use {@code getLearningRateMultiplierValue()} ({@link #learningRateMultiplierValue}) instead
         */
        @Deprecated
        public Long getLearningRateMultiplier() {
            Double value = this.learningRateMultiplierValue;
            if (value == null || value.isNaN() || value.isInfinite() || value < -0x1p63 || value >= 0x1p63) {
                return null;
            }
            return value.longValue();
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
