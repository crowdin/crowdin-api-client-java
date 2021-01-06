package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CrowdinVendorTranslatedTaskCreateFormRequest extends AddTaskRequest {

    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Integer type;
    private String vendor;
    private Status status;
    private String description;
    private String expertise;
    private String subject;
    private List<Long> labelIds;
    private Date dateFrom;
    private Date dateTo;
}
