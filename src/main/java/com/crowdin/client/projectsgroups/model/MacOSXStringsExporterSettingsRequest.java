package com.crowdin.client.projectsgroups.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class MacOSXStringsExporterSettingsRequest extends StringsExporterSettingsRequest {
    @Setter(AccessLevel.NONE)
    private String format = "macosx";

    private MacOSXStringsExporterSettings settings;
}
