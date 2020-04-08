package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WebhookResponseList {

    private List<WebhookResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Webhook> to(WebhookResponseList webhookResponseList) {
        return ResponseList.of(
                webhookResponseList.getData().stream()
                        .map(WebhookResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                webhookResponseList.getPagination()
        );
    }
}
