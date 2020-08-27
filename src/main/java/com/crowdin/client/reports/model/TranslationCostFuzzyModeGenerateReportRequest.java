package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;
import lombok.Data;

import java.util.List;

@Data
public class TranslationCostFuzzyModeGenerateReportRequest extends GenerateReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static class Schema {

        private Unit unit;
        private Currency currency;
        private GroupBy groupBy;
        private String languageId;
        private ReportsFormat format;
        private List<StepType> stepTypes;
    }

    public static abstract class StepType {
    }

    @Data
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

    public enum GroupBy implements EnumConverter<GroupBy> {

        USER, LANGUAGE;

        public static GroupBy from(String value) {
            return GroupBy.valueOf(value.toUpperCase());
        }

        @Override
        public String to(GroupBy v) {
            return v.name().toLowerCase();
        }
    }
}
