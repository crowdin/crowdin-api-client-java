package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class AddVoteRequest {

    private Mark mark;
    private Long translationId;
}
