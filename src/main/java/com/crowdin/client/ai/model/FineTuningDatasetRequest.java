package com.crowdin.client.ai.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FineTuningDatasetRequest {
    private List<Long> projectIds;
    private List<Long> tmIds;
    private String purpose;
    private Date dateFrom;
    private Date dateTo;
    private Long maxFileSize;
    private Long minExamplesCount;
    private Long maxExamplesCount;
}
