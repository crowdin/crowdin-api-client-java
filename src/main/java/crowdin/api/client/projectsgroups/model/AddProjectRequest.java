package crowdin.api.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class AddProjectRequest {

    private String name;
    private String sourceLanguageId;
    private Integer templateId;
    private Integer groupId;
    private List<String> targetLanguageIds;
    private Integer vendorId;
    private Integer mtEngineId;
    private String description;
}
