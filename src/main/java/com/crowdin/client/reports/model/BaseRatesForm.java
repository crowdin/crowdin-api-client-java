package com.crowdin.client.reports.model;

import lombok.Data;

@Data
public class BaseRatesForm {
    private Float fullTranslation;
    private Float proofread;
    /**
     * Hourly rate (used by hourly report settings templates and time-spent reports)
     */
    private Float hourly;
}
