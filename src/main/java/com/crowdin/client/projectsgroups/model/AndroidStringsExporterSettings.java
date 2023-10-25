package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AndroidStringsExporterSettings extends StringsExporterSettings {
    private Boolean convertPlaceholders;
}
