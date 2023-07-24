package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrgWebhookResponseList {
    private List<OrgWebhookResponseObject> data;
    private Pagination pagination;

    public static ResponseList<OrganizationWebhook> to(OrgWebhookResponseList responseList) {
        return ResponseList.of(
                responseList.getData().stream()
                        .map(OrgWebhookResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
