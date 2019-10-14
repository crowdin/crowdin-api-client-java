package crowdin.api.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class Project {

    public int id;
    public int groupId;
    public int userId;
    public String sourceLanguageId;
    public List<String> targetLanguageIds;
    public String joinPolicy;
    public String languageAccessPolicy;
    public int type;
    public String name;
    public String cname;
    public String identifier;
    public String description;
    public String visibility;
    public String logo;
    public String background;
    public boolean isExternal;
    public String externalType;
    public int advancedWorkflowId;
    public boolean hasCrowdsourcing;
    public String createdAt;
    public String updatedAt;

}
