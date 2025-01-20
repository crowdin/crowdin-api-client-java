package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class MtPreTranslate {

    private Boolean enabled;
    private List<MtItem> mts;

    @Data
    public static class MtItem {
        private Long mtId;
        private List<String> languageIds;
    }
}
