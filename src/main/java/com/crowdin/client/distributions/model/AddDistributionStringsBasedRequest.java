package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.List;

@Data
public class AddDistributionStringsBasedRequest {
    private String name;
    private List<Integer> bundleIds;
}
