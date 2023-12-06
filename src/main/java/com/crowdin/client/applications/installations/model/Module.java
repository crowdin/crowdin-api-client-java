package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.Map;

@Data
public class Module {

    private String key;
    private String type;
    private Map<String, Object> data;
    private String authenticationType;

}
