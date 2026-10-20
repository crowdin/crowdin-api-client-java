package com.crowdin.client.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class AddProjectMemberRequest {

    private List<Long> userIds;
    /**
     * @deprecated the API expects an array of usernames, use {@link #usernameList} instead.
     * The legacy setter splits a comma-separated string into {@link #usernameList}.
     */
    @Deprecated
    @JsonIgnore
    private String usernames;
    @JsonProperty("usernames")
    private List<String> usernameList;
    private List<String> emails;
    /**
     * @deprecated deprecated in the API, use {@link #roles} instead
     */
    @Deprecated
    private Boolean accessToAllWorkflowSteps;
    private Boolean managerAccess;
    private Boolean developerAccess;
    /**
     * @deprecated deprecated in the API, use {@link #roles} instead
     */
    @Deprecated
    private Map<String, LanguagePermission> permissions;
    private List<TranslatorRole> roles;
    /**
     * Crowdin only. Invitation message
     */
    private String message;

    /**
     * @return usernames joined with a comma
     * @deprecated use {@link #usernameList} instead
     */
    @Deprecated
    public String getUsernames() {
        return this.usernameList == null ? null : String.join(",", this.usernameList);
    }

    /**
     * @param usernames comma-separated usernames
     * @deprecated use {@link #usernameList} instead
     */
    @Deprecated
    public void setUsernames(String usernames) {
        if (usernames == null) {
            this.usernameList = null;
            return;
        }
        List<String> list = new ArrayList<>();
        for (String username : usernames.split(",")) {
            String trimmed = username.trim();
            if (!trimmed.isEmpty()) {
                list.add(trimmed);
            }
        }
        this.usernameList = list;
    }
}
