package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.List;

@Data
public class AddDistributionRequest {
    private Enum exportMode;
    private String name;
    private List<Long> fileIds;
    private String format;
    private String exportPattern;
    private List<Long> labelIds;
}
