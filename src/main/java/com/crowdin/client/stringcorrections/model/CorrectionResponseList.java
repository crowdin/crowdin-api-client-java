package com.crowdin.client.stringcorrections.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CorrectionResponseList {

    private List<CorrectionResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Correction> to(CorrectionResponseList correctionResponseList) {
        return ResponseList.of(
                correctionResponseList.getData().stream()
                        .map(CorrectionResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                correctionResponseList.getPagination()
        );
    }
}
