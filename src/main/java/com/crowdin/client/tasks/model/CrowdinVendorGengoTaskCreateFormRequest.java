package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CrowdinVendorGengoTaskCreateFormRequest extends AddTaskRequest {

    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Integer type;
    private String vendor;
    private Status status;
    private String description;
    private String expertise;
    private String tone;
    private String purpose;
    private String customerMessage;
    private Boolean usePreferred;
    private Boolean editService;
    private List<Long> labelIds;
    private Date dateFrom;
    private Date dateTo;
}
