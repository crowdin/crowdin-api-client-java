package com.crowdin.client.screenshots.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Screenshot {

    private Long id;
    private Long userId;
    /**
     * @deprecated deprecated in the API, use {@code webUrl} instead
     */
    @Deprecated
    private String url;
    private String webUrl;
    private String name;
    private Size size;
    private Integer tagsCount;
    private List<Tag> tags;
    private Date createdAt;
    private Date updatedAt;
    private Long[] labelIds;
}
