package com.crowdin.client.ai.model;

import java.util.Date;
import lombok.Data;

@Data
public class FineTuningEvent {
    private String id;
    private String type;
    private String message;
    private FineTuningEventData data;
    private Date createdAt;

    @Data
    public static class FineTuningEventData {
        private Long step;
        private Long totalSteps;
        private Double trainingLoss;
        private Double validationLoss;
        private Double fullValidationLoss;
    }
}
