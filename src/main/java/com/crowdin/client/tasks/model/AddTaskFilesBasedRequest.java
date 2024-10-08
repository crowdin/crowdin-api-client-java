package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddTaskFilesBasedRequest extends AddTaskRequest {
    private String languageId;
    private List<Long> fileIds;
    private Status status;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private Date dateFrom;
    private Date dateTo;
}