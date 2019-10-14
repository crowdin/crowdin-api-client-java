package crowdin.api.client.core.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Credentials {

    @NonNull
    private final String token;
    private final String organization;
}
