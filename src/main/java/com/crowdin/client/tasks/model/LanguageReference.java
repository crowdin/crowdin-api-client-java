package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.List;

@Data
public class LanguageReference {
    private String languageId;
    private List<Integer> userIds;
    private List<Integer> teamIds;
}
