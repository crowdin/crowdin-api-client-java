package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumSet;
import java.util.List;

/**
 * Query parameters for listing tasks across projects:
 * List Organization Tasks ({@code GET /tasks}, Enterprise) and List User Project Tasks ({@code GET /users/{userId}/tasks}, Crowdin).
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListAllTasksParams extends Pagination {
    private List<OrderByField> orderBy;
    private EnumSet<Status> statuses;
    private EnumSet<Type> types;
    /**
     * Cannot be used together with {@code groupIds}
     */
    private List<Long> projectIds;
    /**
     * Enterprise only. Cannot be used together with {@code projectIds}
     */
    private List<Long> groupIds;
    private List<Long> assigneeIds;
    private List<Long> creatorIds;
    private List<String> targetLanguageIds;
    private List<String> sourceLanguageIds;
    /**
     * ISO 8601
     */
    private String createdAtFrom;
    /**
     * ISO 8601
     */
    private String createdAtTo;
    /**
     * ISO 8601
     */
    private String deadlineFrom;
    /**
     * ISO 8601
     */
    private String deadlineTo;
}
