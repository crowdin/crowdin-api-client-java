package com.crowdin.client.distributions.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DistributionResponseList {

    private List<DistributionResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Distribution> to(DistributionResponseList distributionResponseList) {
        return ResponseList.of(
                distributionResponseList.getData().stream()
                        .map(DistributionResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                distributionResponseList.getPagination()
        );
    }
}
