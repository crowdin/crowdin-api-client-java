package com.crowdin.client.organization.model;

import lombok.Data;

@Data
public class OrganizationPlan {

    private String name;
    private Long wordsLimit;
    private Long managersLimit;
}
