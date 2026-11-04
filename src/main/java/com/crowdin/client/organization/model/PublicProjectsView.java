package com.crowdin.client.organization.model;

import com.crowdin.client.core.model.EnumConverter;

public enum PublicProjectsView implements EnumConverter<PublicProjectsView> {
    GRID, LIST;

    public static PublicProjectsView from(String value) {
        for (PublicProjectsView v : PublicProjectsView.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(PublicProjectsView value) {
        return value.name().toLowerCase();
    }
}
