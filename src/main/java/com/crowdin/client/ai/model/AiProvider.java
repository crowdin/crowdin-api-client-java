package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiProvider {
    private Date createdAt;
    private Integer promptsCount;
    private Credentials credentials;
    private Boolean isEnabled;
    private String name;
    private Integer id;
    private String type;
    private Config config;
    private Boolean useSystemCredentials;
    private Date updatedAt;
}
