package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class TranslationCostFuzzyModeGenerateReportRequest extends GenerateReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static class Schema {

        private Unit unit;
        private Currency currency;
        private GroupingParameter groupBy;
        private String languageId;
        private ReportsFormat format;
        private List<StepType> stepTypes;
    }

    public static abstract class StepType {
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ProofreadStep extends StepType {
        private String type;
        private String mode;
        private List<ProofreadRegularRate> regularRates;
        private List<ProofreadIndividualRate> individualRates;
    }

    @Data
    public static class ProofreadRegularRate {
        private String mode;
        private double value;
    }

    @Data
    public static class ProofreadIndividualRate {
        private List<String> languageIdsTo;
        private List<ProofreadRegularRate> rates;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TranslateStep extends StepType {

        private String type;
        private String mode;
        private List<TranslateRegularRate> regularRates;
        private List<TranslateIndividualRate> individualRates;
    }

    @Data
    public static class TranslateRegularRate {
        private Mode mode;
        private double value;
    }

    @Data
    public static class TranslateIndividualRate {
        private List<String> languageIdsTo;
        private List<TranslateRegularRate> rates;
    }
}
