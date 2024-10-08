package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddTaskStringsBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> stringIds;
    private Status status;
    private Date dateFrom;
    private Date dateTo;
}
