package com.crowdin.client.workflows.model;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowStep {

    private Long id;
    private String title;
    private String type;
    private List<String> languages;
    private Object config;
}
