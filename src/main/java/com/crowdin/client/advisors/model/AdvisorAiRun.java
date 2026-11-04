package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdvisorAiRun {

    private AdvisorAiMode mode;
    private Long promptId;
    private Date at;
}
