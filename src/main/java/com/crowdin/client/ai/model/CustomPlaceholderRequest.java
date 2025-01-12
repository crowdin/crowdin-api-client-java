package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomPlaceholderRequest {
    private String description;
    private String placeholder;
    private String value;
}
