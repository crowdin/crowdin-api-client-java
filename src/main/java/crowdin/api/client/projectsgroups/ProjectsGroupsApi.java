package crowdin.api.client.projectsgroups;

import crowdin.api.client.core.CrowdinApi;
import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.HttpConfig;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.http.exceptions.HttpBadRequestException;
import crowdin.api.client.core.http.exceptions.HttpException;
import crowdin.api.client.core.model.Credentials;
import crowdin.api.client.core.model.ResponseList;
import crowdin.api.client.projectsgroups.model.Project;
import crowdin.api.client.projectsgroups.model.ProjectResponseList;

import java.util.Map;
import java.util.Optional;

public class ProjectsGroupsApi extends CrowdinApi {

    public ProjectsGroupsApi(Credentials credentials) {
        super(credentials);
    }

    public ProjectsGroupsApi(Credentials credentials, JsonTransformer jsonTransformer) {
        super(credentials, jsonTransformer);
    }

    public ProjectsGroupsApi(Credentials credentials, HttpClient httpClient) {
        super(credentials, httpClient);
    }

    /**
     * @param groupId          group identifier (optional)
     * @param hasManagerAccess projects with manager access (default 0)
     * @param limit            maximum number of items to retrieve (default 25)
     * @param offset           starting offset in the collection (default 0)
     * @return list of projects
     */
    public ResponseList<Project> listProjects(Integer groupId, Integer hasManagerAccess, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "hasManagerAccess", Optional.ofNullable(hasManagerAccess),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ProjectResponseList projectResponseList = this.httpClient.get(this.url + "/projects", new HttpConfig(queryParams), ProjectResponseList.class);
        return ProjectResponseList.to(projectResponseList);
    }
}
