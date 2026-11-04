package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.List;

@Data
public class AdvisorCheckAttributes {

    private String category;
    private List<AdvisorCheckInspector> inspectors;
}
