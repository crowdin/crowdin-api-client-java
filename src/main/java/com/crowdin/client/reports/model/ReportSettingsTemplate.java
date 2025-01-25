package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Boolean isGlobal;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class OrganizationReportSettingsTemplate extends ReportSettingsTemplate {

        private Long projectId;
        private Long groupId;
    }

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
