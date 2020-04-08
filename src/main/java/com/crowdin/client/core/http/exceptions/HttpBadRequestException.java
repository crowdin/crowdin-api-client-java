package com.crowdin.client.core.http.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpBadRequestException extends RuntimeException {

    public List<ErrorHolder> errors;

    @Data
    public static class ErrorHolder {

        public ErrorKey error;
    }

    @Data
    public static class ErrorKey {

        public String key;
        public List<Error> errors;

    }

    @Data
    public static class Error {

        public String code;
        public String message;

    }
}
