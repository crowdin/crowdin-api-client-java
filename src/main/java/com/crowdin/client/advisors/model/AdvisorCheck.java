package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdvisorCheck {

    private String identifier;
    /**
     * Status of the underlying job (e.g. created, in_progress, done, failed)
     */
    private String status;
    private Integer progress;
    private AdvisorCheckAttributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
}
