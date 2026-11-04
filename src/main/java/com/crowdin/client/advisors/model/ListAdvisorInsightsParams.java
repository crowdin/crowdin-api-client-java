package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListAdvisorInsightsParams extends Pagination {

    private Boolean isDismissed;
    private List<AdvisorInsightStatus> status;
    private List<AdvisorInsightOutcome> outcome;
}
