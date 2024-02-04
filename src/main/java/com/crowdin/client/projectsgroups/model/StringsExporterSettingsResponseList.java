package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StringsExporterSettingsResponseList {
    private List<StringsExporterSettingsResponseObject> data;
    private Pagination pagination;

    public static ResponseList<StringsExporterSettingsResource> to(StringsExporterSettingsResponseList responseList) {
        return ResponseList.of(
                responseList.getData().stream()
                        .map(StringsExporterSettingsResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
