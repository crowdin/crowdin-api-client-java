package com.crowdin.client.tasks.model;

import com.crowdin.client.languages.model.Language;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Task {

    private Long id;
    private Long projectId;
    private Long creatorId;
    private Type type;
    private String vendor;
    private Status status;
    private String title;
    private List<Assignee> assignees;
    private List<AssignedTeam> assignedTeams;
    private List<Long> fileIds;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private Progress progress;
    @Deprecated
    private TranslateProgress translateProgress;
    private String sourceLanguageId;
    private String targetLanguageId;
    private String description;
    private String translationUrl;
    private String webUrl;
    private Integer wordsCount;
    private Integer filesCount;
    private Integer commentsCount;
    private Date deadline;
    private Date startedAt;
    private Date resolvedAt;
    private String timeRange;
    private Long workflowStepId;
    private String buyUrl;
    private Date createdAt;
    private Date updatedAt;
    private Language sourceLanguage;
    private List<Language> targetLanguages;
    private Object fields;
    private Long precedingTaskId;
}
