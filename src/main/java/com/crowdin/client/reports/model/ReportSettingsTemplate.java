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
    /**
     * Report unit. {@link Unit#HOURS} is used by hourly templates, other units by post-editing templates.
     */
    private Unit unit;
    private String mode;
    private Config config;
    private Date createdAt;
    private Date updatedAt;
    /**
     * Applies to project report settings templates (and organization ones in Crowdin Enterprise); not supported by user templates
     */
    private Boolean isPublic;
    /**
     * Applies to project report settings templates only; not supported by user and organization templates
     */
    private Boolean isGlobal;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class OrganizationReportSettingsTemplate extends ReportSettingsTemplate {

        private Long projectId;
        private Long groupId;
    }

    /**
     * Report settings template configuration.
     * <p>
     * Post-editing templates use {@code baseRates} (fullTranslation, proofread), {@code individualRates},
     * {@code netRateSchemes} and the boolean options.
     * Hourly templates ({@code unit = hours}) use {@code baseRates} (hourly) and {@code individualRates} (hourly) only.
     */
    @Data
    public static class Config {
        /**
         * @deprecated obsolete configuration format, use {@link #baseRates} and {@link #netRateSchemes} instead
         */
        @Deprecated
        private List<RegularRate> regularRates;
        private List<IndividualRate> individualRates;
        private BaseRatesForm baseRates;
        private NetRateSchemes netRateSchemes;
        private Boolean calculateInternalMatches;
        private Boolean includePreTranslatedStrings;
        private Boolean useCategoryBasedProofreadRates;
        private Boolean useTmEditDistance;
    }

    /**
     * @deprecated obsolete configuration format, use {@code Config#getBaseRates()} and {@code Config#getNetRateSchemes()} instead
     */
    @Deprecated
    @Data
    public static class RegularRate {
        private Mode mode;
        private double value;
    }

    /**
     * @deprecated obsolete configuration format, use {@code Config#getNetRateSchemes()} instead
     */
    @Deprecated
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
