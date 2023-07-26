package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class TmPenalties {
    private Integer autoSubstitution;
    private TmPriority tmPriority;
    private Integer multipleTranslations;
    private TmTimeElapsed timeSinceLastUsage;
    private TmTimeElapsed timeSinceLastModified;
}
