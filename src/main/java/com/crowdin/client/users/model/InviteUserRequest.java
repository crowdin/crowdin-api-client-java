package com.crowdin.client.users.model;

import lombok.Data;

@Data
public class InviteUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String timezone;
}
