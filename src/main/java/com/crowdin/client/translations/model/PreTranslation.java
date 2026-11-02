package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;
import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PreTranslation {
    private String identifier;
    private Status status;
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
        private String method;
        private String autoApproveOption;
        private Boolean duplicateTranslations;
        private Boolean skipApprovedTranslations;
        /**
         * @deprecated reflects the legacy request value; use {@link #scope} instead.
         */
        @Deprecated
        private Boolean translateUntranslatedOnly;
        private Scope scope;
        private String translationModifiedBefore;
        private String translationModifiedAfter;
        private ReplaceTranslationsOption replaceTranslationsOption;
        private Boolean resetApprovalStatus;
        private Boolean translateWithPerfectMatchOnly;
        private Priority priority;
        private Boolean notifyOnCompletion;
        /**
         * Enterprise only
         */
        private Integer minimumMatchRatio;
    }

    public enum Status implements EnumConverter<Status> {
        CREATED("created"),
        IN_PROGRESS("in_progress"),
        CANCELED("canceled"),
        FAILED("failed"),
        FINISHED("finished");

        private String value;

        Status(String value) {
            this.value = value;
        }

        public static Status from(String value) {
            for (Status s : Status.values()) {
                if (s.value.equals(value)) {
                    return s;
                }
            }
            return null;
        }

        @Override
        public Object to(Status v) {
            return this.value;
        }
    }
}
