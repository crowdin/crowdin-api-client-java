package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.Date;

@Data
public class DistributionStringsBasedRelease {
    private String status;
    private Integer progress;
    private String currentLanguageId;
    private Long currentBranchId;
    private Date date;
}
