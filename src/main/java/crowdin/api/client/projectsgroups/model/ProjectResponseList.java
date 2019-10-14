package crowdin.api.client.projectsgroups.model;

import crowdin.api.client.core.model.Pagination;
import crowdin.api.client.core.model.ResponseList;
import crowdin.api.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectResponseList {

    public List<ProjectResponseObject> data;
    public Pagination pagination;

    public static ResponseList<Project> to(ProjectResponseList projectResponseList) {
        return ResponseList.of(
                projectResponseList.getData().stream()
                        .map(ProjectResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                projectResponseList.getPagination()
        );
    }
}
