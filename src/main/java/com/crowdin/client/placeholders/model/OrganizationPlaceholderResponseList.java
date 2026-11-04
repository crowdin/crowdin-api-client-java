package com.crowdin.client.placeholders.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrganizationPlaceholderResponseList {

    private List<OrganizationPlaceholderResponseObject> data;
    private Pagination pagination;

    public static ResponseList<OrganizationPlaceholder> to(OrganizationPlaceholderResponseList organizationPlaceholderResponseList) {
        return ResponseList.of(
                organizationPlaceholderResponseList.getData().stream()
                        .map(OrganizationPlaceholderResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                organizationPlaceholderResponseList.getPagination()
        );
    }
}
