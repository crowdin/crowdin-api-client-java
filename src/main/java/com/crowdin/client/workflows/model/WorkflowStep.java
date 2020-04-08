package com.crowdin.client.workflows.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WorkflowStep {

    private Long id;
    private String title;
    private String type;
    private List<String> languages;
    private Config config;

    @Data
    public static class Config {
        private Map<String, List<Integer>> assignees;
    }
}
