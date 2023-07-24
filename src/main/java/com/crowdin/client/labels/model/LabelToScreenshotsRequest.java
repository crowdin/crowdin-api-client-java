package com.crowdin.client.labels.model;

import lombok.Data;

import java.util.List;

@Data
public class LabelToScreenshotsRequest {

    private List<Long> screenshotIds;
}
