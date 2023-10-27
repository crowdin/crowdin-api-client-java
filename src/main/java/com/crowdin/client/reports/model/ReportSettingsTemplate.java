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
    private Boolean isPublic;

    @Data
    public static class Config {
        private List<RegularRate> regularRates;
        private List<IndividualRate> individualRates;
    }

    @Data
    public static class RegularRate {
        private Mode mode;
        private double value;
    }

    @Data
    public static class IndividualRate {
        private List<String> languageIds;
        private List<Integer> userIds;
        private List<RegularRate> rates;
    }

    public enum Mode implements EnumConverter<Mode> {

        NO_MATCH, TM_MATCH;

        public static Mode from(String value) {
            return Mode.valueOf(value.toUpperCase());
        }

        @Override
        public String to(Mode v) {
            return v.name().toLowerCase();
        }
    }
}
