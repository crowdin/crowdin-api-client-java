package com.crowdin.client.screenshots.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ScreenshotResponseList {

    private List<ScreenshotResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Screenshot> to(ScreenshotResponseList screenshotResponseList) {
        return ResponseList.of(
                screenshotResponseList.getData().stream()
                        .map(ScreenshotResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                screenshotResponseList.getPagination()
        );
    }
}
