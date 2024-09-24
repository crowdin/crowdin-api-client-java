package com.crowdin.client.core.http.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpBatchBadRequestException extends CrowdinApiException {
    private final List<BatchErrors> errors;

    public HttpBatchBadRequestException() {
        this.errors = Collections.emptyList();
    }

    @Data
    public static class BatchErrors implements Serializable {
        private Integer index;
        private List<ErrorHolder> errors;

    }

    @Data
    public static class ErrorHolder {
        private ErrorKey error;
    }

    @Data
    public static class ErrorKey implements Serializable {
        private String key;
        private List<Error> errors;
    }

    @Data
    public static class Error {
        private String code;
        private String message;
    }
}
