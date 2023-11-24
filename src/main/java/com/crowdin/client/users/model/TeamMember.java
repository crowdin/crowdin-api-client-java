package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class TeamMember {

    private Long id;
    private String username;
    private String fullName;
    private String role;
    /**
     * @deprecated
     */
    private Map<String, String> permissions;
    private String avatarUrl;
    private Date joinedAt;
    private String timezone;
    private List<TranslatorRole> roles;
}
