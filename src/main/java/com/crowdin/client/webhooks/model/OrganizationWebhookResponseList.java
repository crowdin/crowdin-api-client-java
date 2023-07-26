package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrganizationWebhookResponseList {
    private List<OrganizationWebhookResponseObject> data;
    private Pagination pagination;

    public static ResponseList<OrganizationWebhook> to(OrganizationWebhookResponseList responseList) {
        return ResponseList.of(
                responseList.getData().stream()
                        .map(OrganizationWebhookResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
