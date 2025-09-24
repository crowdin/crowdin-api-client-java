package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListTasksParams extends Pagination {
    private EnumSet<Status> statuses;
    private Long assigneeId;
}
