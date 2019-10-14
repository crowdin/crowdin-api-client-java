package crowdin.api.client.core.model;

import lombok.Data;

@Data
public class Pagination {
    public int offset;
    public int limit;
}
