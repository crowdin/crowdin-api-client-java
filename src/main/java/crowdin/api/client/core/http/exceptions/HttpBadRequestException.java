package crowdin.api.client.core.http.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpBadRequestException extends RuntimeException {

    public List<ErrorKey> errors;

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
