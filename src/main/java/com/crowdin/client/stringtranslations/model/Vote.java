package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class Vote {

    private Long id;
    private User user;
    private Long translationId;
    private Date votedAt;
    private Mark mark;
}
