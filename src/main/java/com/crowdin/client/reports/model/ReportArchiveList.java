package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReportArchiveList {
    private List<ReportArchiveResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ReportArchive> to(ReportArchiveList reportArchiveList) {
        return ResponseList.of(
                reportArchiveList.getData().stream()
                        .map(ReportArchiveResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                reportArchiveList.getPagination()
        );
    }
}
