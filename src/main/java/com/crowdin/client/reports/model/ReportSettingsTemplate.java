package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportSettingsTemplate {

    private Long id;
    private String name;
    private Currency currency;
    private Unit unit;
    private String mode;
    private Config config;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class Config {
        private List<ProofreadRegularRate> regularRates;
        private List<ProofreadIndividualRate> individualRates;
    }

    @Data
    public static class ProofreadRegularRate {
        private Mode mode;
        private double value;
    }

    @Data
    public static class ProofreadIndividualRate {
        private List<String> languageIds;
        private List<Integer> userIds;
        private List<ProofreadRegularRate> rates;
    }

    public enum Mode implements EnumConverter<Mode> {

        NO_MATCH("no_match"), TM_MATCH("tm_match");

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
            return v.name().toLowerCase();
        }
    }
}
