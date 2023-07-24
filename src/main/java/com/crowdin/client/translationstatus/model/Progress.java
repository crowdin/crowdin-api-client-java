package com.crowdin.client.translationstatus.model;

import lombok.Data;

@Data
public class Progress {

    private Words words;
    private Words phrases;
    private Integer translationProgress;
    private Integer approvalProgress;
    private String eTag;

    @Data
    public static class Words {
        private Integer total;
        private Integer translated;
        private Integer preTranslateAppliedTo;
        private Integer approved;
    }
}
