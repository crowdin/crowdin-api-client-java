package com.crowdin.client.stringcomments.model;

import lombok.Data;

@Data
public class Attachment {
    private Long id;
    private String name;
    private String mime;
    private Long size;
    private String category;
    private String thumbnailUrl;
    private String url;
    private String downloadUrl;
}
