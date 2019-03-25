package com.crowdin.exception;


@SuppressWarnings("WeakerAccess")
public class CrowdinException extends RuntimeException {
    public CrowdinException() {
    }

    public CrowdinException(String message) {
        super(message);
    }

    public CrowdinException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrowdinException(Throwable cause) {
        super(cause);
    }

    public CrowdinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
