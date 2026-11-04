package com.crowdin.client.organization.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class Organization {

    private Long id;
    private String domain;
    private String name;
    private String logo;
    private String defaultLogo;
    private String description;
    private String internalDescription;
    private String cname;
    /**
     * Whether the organization is a vendor. Set when the API returns a JSON boolean or the string {@code "true"} /
     * {@code "false"}; {@code null} for any other value, which is kept in {@link #isVendorRaw}.
     */
    @JsonIgnore
    private Boolean isVendor;
    /**
     * Raw {@code isVendor} value as text (e.g. {@code "false"}). The API documents {@code isVendor} as a string
     * while its example shows a boolean, so the original value is always kept here.
     */
    @JsonIgnore
    private String isVendorRaw;
    private PublicProjectsView defaultPublicProjectsView;
    private OrganizationPlan plan;
    private List<OrganizationDefault> defaults;

    /**
     * Reads {@code isVendor} returned either as a JSON boolean or as a string
     */
    @JsonSetter("isVendor")
    private void readIsVendor(JsonNode value) {
        if (value == null || value.isNull()) {
            this.isVendor = null;
            this.isVendorRaw = null;
            return;
        }
        this.isVendorRaw = value.isTextual() ? value.textValue() : value.toString();
        if (value.isBoolean()) {
            this.isVendor = value.booleanValue();
        } else if ("true".equalsIgnoreCase(this.isVendorRaw) || "false".equalsIgnoreCase(this.isVendorRaw)) {
            this.isVendor = Boolean.valueOf(this.isVendorRaw);
        } else {
            this.isVendor = null;
        }
    }

    /**
     * Writes {@code isVendor}: the boolean if known, otherwise the raw value
     */
    @JsonGetter("isVendor")
    private Object writeIsVendor() {
        return this.isVendor != null ? this.isVendor : this.isVendorRaw;
    }
}
