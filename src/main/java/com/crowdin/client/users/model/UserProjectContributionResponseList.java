package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserProjectContributionResponseList {

    private List<UserProjectContributionResponseObject> data;
    private Pagination pagination;

    public static ResponseList<UserProjectContribution> to(UserProjectContributionResponseList userProjectContributionResponseList) {
        return ResponseList.of(
                userProjectContributionResponseList.getData().stream()
                        .map(UserProjectContributionResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                userProjectContributionResponseList.getPagination()
        );
    }
}
