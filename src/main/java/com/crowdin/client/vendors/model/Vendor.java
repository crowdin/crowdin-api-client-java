package com.crowdin.client.vendors.model;

import lombok.Data;

@Data
public class Vendor {

    private Long id;
    private String name;
    private String description;
    private Status status;
}
