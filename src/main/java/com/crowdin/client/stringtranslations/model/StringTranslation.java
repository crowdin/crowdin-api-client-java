package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class StringTranslation {

    private Long id;
    private String text;
    private PluralCategoryName pluralCategoryName;
    private User user;
    private Integer rating;
    private Date createdAt;
}
