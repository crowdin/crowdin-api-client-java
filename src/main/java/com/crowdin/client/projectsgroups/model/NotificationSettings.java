package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class NotificationSettings {

    private Boolean translatorNewStrings;
    private Boolean managerNewStrings;
    private Boolean managerLanguageCompleted;
}
