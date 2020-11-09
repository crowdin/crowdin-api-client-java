package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
}
