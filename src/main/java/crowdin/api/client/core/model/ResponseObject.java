package crowdin.api.client.core.model;

import lombok.Data;

@Data
public class ResponseObject<T> {
    public T data;

    public static <T> ResponseObject<T> of(T data) {
        ResponseObject<T> responseObject = new ResponseObject<T>();
        responseObject.setData(data);
        return responseObject;
    }
}
