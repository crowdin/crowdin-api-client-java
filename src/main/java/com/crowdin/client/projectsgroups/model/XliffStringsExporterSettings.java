package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class XliffStringsExporterSettings extends StringsExporterSettings {
    private Map<String, String> languagePairMapping;
}
