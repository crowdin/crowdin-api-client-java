package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CostEstimateFuzzyModeGenerateReportRequest extends GenerateReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static class Schema {

        private Unit unit;
        private Currency currency;
        private String languageId;
        private Reports2Format format;
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
        private Boolean calculateInternalFuzzyMatches;
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

    public enum Mode implements EnumConverter<Mode> {

        NO_MATCH("no_match"), PERFECT("perfect"), OPTION_100("100"), OPTION_99_95("99-95"), OPTION_94_90("94-90"), OPTION_89_80("89-80");

        private final String val;

        Mode(String val) {
            this.val = val;
        }

        public static Mode from(String value) {
            for (Mode mode : Mode.values()) {
                if (mode.val.equals(value)) {
                    return mode;
                }
            }
            return null;
        }

        @Override
        public String to(Mode v) {
            return v.val;
        }
    }
}
