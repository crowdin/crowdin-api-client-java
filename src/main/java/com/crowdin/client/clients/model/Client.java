package com.crowdin.client.clients.model;

import lombok.Data;

@Data
public class Client {

    private Long id;
    private String name;
    private String description;
    private Status status;
}
