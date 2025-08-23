package com.crowdin.client.core.http.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpException extends CrowdinApiException {

    public Error error;
    public String httpResponse;

    @Data
    public static class Error {

        public String code;
        public String message;

    }

    public static HttpException fromMessage(String message, String httpResponse) {
        var resp = new HttpException();
        var error = new Error();
        error.setMessage(message);
        resp.setError(error);
        resp.setHttpResponse(httpResponse);
        return resp;
    }
}
