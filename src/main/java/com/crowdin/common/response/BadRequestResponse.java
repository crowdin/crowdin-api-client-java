package com.crowdin.common.response;

import java.util.*;
import java.util.stream.Collectors;

public class BadRequestResponse implements Response {

    private List<Object> errors;

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Bad Request: " + extractMessages().stream().map(BadRequestMessages::toString).collect(Collectors.joining(", "));
    }

    public static class BadRequestMessages {

        private Object key;
        private String name;
        private List<ParamError> errors;

        private BadRequestMessages() {
            this.errors = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String
                    .format(
                            "Parameter name: \"%s\", errors: \"%s\"",
                            Optional.ofNullable(name).orElse(key.toString()),
                            errors.stream().map(ParamError::toString).collect(Collectors.joining(", "))
                    );
        }
    }

    public static class ParamError {
        private String errorDescription;
        private String errorMessage;

        @Override
        public String toString() {
            return errorDescription + " -> " + errorMessage;
        }
    }

    private List<BadRequestMessages> extractMessages() {
        List<BadRequestMessages> results = new ArrayList<>();


        for (Object error : errors) {
            BadRequestMessages badRequestMessages = new BadRequestMessages();
            badRequestMessages.key = getValueFromJsonNode(error, "key", "error");

            List<Object> errors = getValueFromJsonNode(error, "errors", "error");
            errors
                    .forEach(o -> {
                        String code = getValueFromJsonNode(o, "code").toString();
                        if (code.equals("key")) {
                            badRequestMessages.name = getValueFromJsonNode(o, "message").toString();
                        } else if (code.equals("errors")) {
                            List<Object> messages = getValueFromJsonNode(o, "message");
                            badRequestMessages.errors = messages
                                    .stream()
                                    .map(this::getParamError)
                                    .collect(Collectors.toList());
                        } else {
                            badRequestMessages.errors = Collections.singletonList(getParamError(o));
                        }
                    });

            results.add(badRequestMessages);
        }

        return results;
    }

    private ParamError getParamError(Object jsonNode) {
        ParamError paramError = new ParamError();
        paramError.errorDescription = getValueFromJsonNode(jsonNode, "code").toString();
        paramError.errorMessage = getValueFromJsonNode(jsonNode, "message").toString();
        return paramError;
    }

    @SuppressWarnings("unchecked")
    private <T> T getValueFromJsonNode(Object error, String objectKey, String... intermediateKeys) {
        Map currentDepthEntity = (Map) error;

        for (String key : intermediateKeys) {
            currentDepthEntity = (Map) currentDepthEntity.get(key);
        }

        return (T) currentDepthEntity.get(objectKey);
    }
}
