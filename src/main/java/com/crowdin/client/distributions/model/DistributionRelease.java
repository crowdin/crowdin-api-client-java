package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.Date;

@Data
public class DistributionRelease {
    private String status;
    private Integer progress;
    private String currentLanguageId;
    private Long currentFileId;
    private Date date;
}
