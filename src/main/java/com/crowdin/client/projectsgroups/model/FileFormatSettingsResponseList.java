package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileFormatSettingsResponseList {
    private List<FileFormatSettingsResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FileFormatSettingsResource> to(FileFormatSettingsResponseList responseList) {
        return ResponseList.of(
                responseList.getData().stream()
                        .map(FileFormatSettingsResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
