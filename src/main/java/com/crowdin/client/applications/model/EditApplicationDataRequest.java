package com.crowdin.client.applications.model;

import lombok.Data;

import java.util.Map;

@Data
public class EditApplicationDataRequest {
    private Map<String, Object> data;

}
