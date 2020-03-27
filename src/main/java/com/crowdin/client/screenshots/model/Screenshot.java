package com.crowdin.client.screenshots.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Screenshot {

    private Long id;
    private Long userId;
    private String url;
    private String name;
    private Size size;
    private Integer tagsCount;
    private List<Tag> tags;
    private Date createdAt;
    private Date updatedAt;
}
