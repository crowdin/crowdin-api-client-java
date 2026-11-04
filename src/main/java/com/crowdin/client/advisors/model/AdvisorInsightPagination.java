package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Pagination returned by List Advisor Insights, which additionally carries the {@link #total} number of insights.
 * {@link com.crowdin.client.core.model.ResponseList} exposes it as {@link Pagination}; cast it to this class to read the total.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AdvisorInsightPagination extends Pagination {

    private Integer total;
}
