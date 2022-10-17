package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.List;

@Data
public class AddDistributionRequest {
    private String name;
    private List<Long> fileIds;
}
