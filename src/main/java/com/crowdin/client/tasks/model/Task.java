package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Task {

    private Long id;
    private Long projectId;
    private Long creatorId;
    private Integer type;
    private Status status;
    private String title;
    private List<Assignee> assignees;
    private List<Long> fileIds;
    private Progress progress;
    private String sourceLanguageId;
    private String targetLanguageId;
    private String description;
    private String hash;
    private String translationUrl;
    private Integer wordsCount;
    private Integer filesCount;
    private Integer commentsCount;
    private Date deadline;
    private String timeRange;
    private Long workflowStepId;
    private Date createdAt;
    private Date updatedAt;
    private boolean isArchived;
}
