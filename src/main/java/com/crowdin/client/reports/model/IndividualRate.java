package com.crowdin.client.reports.model;

import lombok.Data;

import java.util.List;

@Data
public class IndividualRate {
    private List<String> languageIds;
    private List<Long> userIds;
    private Float fullTranslation;
    private Float proofread;
}
