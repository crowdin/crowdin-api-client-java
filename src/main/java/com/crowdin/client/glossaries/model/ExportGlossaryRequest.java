package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportGlossaryRequest {

    private GlossariesFormat format;
    private List<String> exportFields;
    private ExportType exportType;
    private List<Status> statuses;
    private List<PartOfSpeech> partsOfSpeech;
    private List<Type> types;
    private List<Gender> genders;
    private List<Long> authorIds;
    private List<String> languageIds;
}
