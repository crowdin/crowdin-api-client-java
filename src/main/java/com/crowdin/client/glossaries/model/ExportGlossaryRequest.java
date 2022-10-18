package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportGlossaryRequest {

    private GlossariesFormat format;
    private List<String> exportFields;
}
