package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Event implements EnumConverter<Event> {
    FILE_TRANSLATED, FILE_APPROVED, PROJECT_TRANSLATED, PROJECT_APPROVED, TRANSLATION_UPDATED,
    SUGGESTION_ADDED, SUGGESTION_UPDATED, SUGGESTION_DELETED, SUGGESTION_APPROVED, SUGGESTION_DISAPPROVED;

    public static Event from(String value) {
        return Event.valueOf(value.toUpperCase().replace(".", "_"));
    }

    @Override
    public Object to(Event v) {
        return v.name().toLowerCase().replace("_", ".");
    }
}
