package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiProvider {
    private Long id;
    private String name;
    private String type;
    private Credentials credentials;
    private Config config;
    private Boolean isEnabled;
    private Boolean useSystemCredentials;
    private Date createdAt;
    private Date updatedAt;
    private Integer promptsCount;
}
