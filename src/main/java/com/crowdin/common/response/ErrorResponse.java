package com.crowdin.common.response;

import com.crowdin.common.models.ApiError;

public class ErrorResponse implements Response {
    private ApiError error;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
