package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CrowdinTaskCreateFormRequest extends AddTaskRequest {

    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Integer type;
    private Status status;
    private String description;
    private Boolean splitFiles;
    private Boolean skipAssignedStrings;
    private Boolean skipUntranslatedStrings;
    private List<Long> labelIds;
    private List<AssigneeRequest> assignees;
    private Date deadline;
    private Date dateFrom;
    private Date dateTo;
}
