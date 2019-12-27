package com.crowdin.common.request;

public class RestoreFilePayload implements Request {

    public Long getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(Long revisionId) {
        this.revisionId = revisionId;
    }

    private Long revisionId;
}
