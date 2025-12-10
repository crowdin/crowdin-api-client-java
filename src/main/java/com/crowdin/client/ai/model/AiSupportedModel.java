package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiSupportedModel {
    private Long providerId;
    private String providerType;
    private String providerName;
    private String id;
    private String displayName;
    private Boolean supportReasoning;
    private Integer intelligence;
    private Integer speed;
    private Price price;
    private Modality modalities;
    private Integer contextWindow;
    private Integer maxOutputTokens;
    private Date knowledgeCutoff;
    private Date releaseDate;
    private Feature features;

    @Data
    public static class Price {
        private Double input;
        private Double output;
    }

    @Data
    public static class Modality {
        private ModalityConfig input;
        private ModalityConfig output;

        @Data
        public static class ModalityConfig {
            private Boolean text;
            private Boolean image;
            private Boolean audio;
        }
    }

    @Data
    public static class Feature {
        private Boolean streaming;
        private Boolean structuredOutput;
        private Boolean functionCalling;
    }
}
