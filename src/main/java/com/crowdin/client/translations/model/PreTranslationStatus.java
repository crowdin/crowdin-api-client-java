package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PreTranslationStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;

    @Data
    public static class Attributes {
        private List<String> languageIds;
        private List<Long> fileIds;
        private List<Long> directoryIds;
        private List<Long> branchIds;
        /**
         * Present only when the auto-translation was requested by {@code taskId}
         */
        private Long taskId;
        private Method method;
        private AutoApproveOption autoApproveOption;
        private boolean duplicateTranslations;
        private boolean skipApprovedTranslations;
        /**
         * @deprecated reflects the legacy request value; use {@link #scope} instead.
         */
        @Deprecated
        private boolean translateUntranslatedOnly;
        private Scope scope;
        private String translationModifiedBefore;
        private String translationModifiedAfter;
        private ReplaceTranslationsOption replaceTranslationsOption;
        private Boolean resetApprovalStatus;
        private boolean translateWithPerfectMatchOnly;
        private Priority priority;
        private Boolean notifyOnCompletion;
        /**
         * Not part of the documented response attributes
         */
        private List<Long> labelIds;
        /**
         * Not part of the documented response attributes
         */
        private List<Long> excludeLabelIds;
        /**
         * Enterprise only
         */
        private Integer minimumMatchRatio;
    }
}
