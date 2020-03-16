package crowdin.api.client.core.model;

import lombok.Data;

@Data
public class PatchRequest {
    private Object value;
    private String path;
}
