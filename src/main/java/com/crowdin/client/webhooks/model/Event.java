package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Event implements EnumConverter<Event> {
    FILE_TRANSLATED, FILE_APPROVED, PROJECT_TRANSLATED, PROJECT_APPROVED, TRANSLATION_UPDATED,
    SUGGESTION_ADDED, SUGGESTION_UPDATED, SUGGESTION_DELETED, SUGGESTION_APPROVED, SUGGESTION_DISAPPROVED,
    FILE_ADDED, FILE_UPDATED, FILE_REVERTED, FILE_DELETED, STRING_ADDED, STRING_UPDATED, STRING_DELETED,
    STRINGCOMMENT_CREATED("stringComment.created"), STRINGCOMMENT_UPDATED("stringComment.updated"),
    STRINGCOMMENT_DELETED("stringComment.deleted"), STRINGCOMMENT_RESTORED("stringComment.restored"),
    TASK_ADDED, TASK_STATUSCHANGED("task.statusChanged"), TASK_DELETED;

    final String rawName;

    Event() {
        rawName = name().toLowerCase().replace("_", ".");
    }

    Event(String rawName) {
        this.rawName = rawName;
    }

    public static Event from(String value) {
        return Event.valueOf(value.toUpperCase().replace(".", "_"));
    }

    @Override
    public Object to(Event v) {
        return rawName;
    }
}
