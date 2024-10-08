package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddEnterpriseTaskStringsBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> stringIds;
    private Date dateTo;
    private Map<String, Object> fields;
}
