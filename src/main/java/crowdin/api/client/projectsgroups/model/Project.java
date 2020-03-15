package crowdin.api.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Project {

    public int id;
    public int groupId;
    public int userId;
    public String sourceLanguageId;
    public List<String> targetLanguageIds;
    public String name;
    public String identifier;
    public String description;
    public String logo;
    public String background;
    public boolean isExternal;
    public String externalType;
    public int workflowId;
    public boolean hasCrowdsourcing;
    public Date createdAt;
    public Date updatedAt;

}
