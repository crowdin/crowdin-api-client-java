package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public abstract class AddTaskStringsBasedRequest {
    private String title;
    private String languageId;
    private List<Long> branchIds;
    private Status status;
    private String description;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private Date dateFrom;
    private Date dateTo;
}
