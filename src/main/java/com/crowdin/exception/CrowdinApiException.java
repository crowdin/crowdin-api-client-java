package com.crowdin.exception;

public class CrowdinApiException extends CrowdinException {

    private static final String REQUEST_ERROR_MASSAGE_PATTERN = "An API error occurred. Internal error code: %d, Message: %s";

    public CrowdinApiException(String message, int errorCode) {
        super(String.format(REQUEST_ERROR_MASSAGE_PATTERN, errorCode, message));
    }
}
