package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.List;

@Data
public class AddDistributionRequest {

    @Deprecated
    private ExportMode exportMode;

    private String name;

    @Deprecated
    private List<Long> fileIds;

    private List<Integer> bundleIds;
}
