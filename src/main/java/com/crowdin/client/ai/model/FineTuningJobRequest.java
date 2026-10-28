package com.crowdin.client.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        /**
         * @deprecated the API expects a number, use {@link #learningRateMultiplierValue} instead
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

        /**
         * @param learningRateMultiplierValue learning rate multiplier
         * @throws IllegalArgumentException if the value is {@code NaN} or infinite, which the API does not accept
         */
        public void setLearningRateMultiplierValue(Double learningRateMultiplierValue) {
            if (learningRateMultiplierValue != null && (learningRateMultiplierValue.isNaN() || learningRateMultiplierValue.isInfinite())) {
                throw new IllegalArgumentException("learningRateMultiplier must be a finite number, got " + learningRateMultiplierValue);
            }
            this.learningRateMultiplierValue = learningRateMultiplierValue;
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
}
