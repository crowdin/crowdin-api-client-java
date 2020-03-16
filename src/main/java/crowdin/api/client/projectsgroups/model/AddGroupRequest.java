package crowdin.api.client.projectsgroups.model;

import lombok.Data;

@Data
public class AddGroupRequest {

    private String name;
    private Integer parentId;
    private String description;
}
