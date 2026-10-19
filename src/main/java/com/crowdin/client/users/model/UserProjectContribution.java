package com.crowdin.client.users.model;

import com.crowdin.client.projectsgroups.model.Project;
import lombok.Data;

@Data
public class UserProjectContribution {

    private Long id;
    private Contribution translated;
    private Contribution approved;
    private Contribution voted;
    private Contribution commented;
    private Project project;

    @Data
    public static class Contribution {
        private Long strings;
        private Long words;
    }
}
