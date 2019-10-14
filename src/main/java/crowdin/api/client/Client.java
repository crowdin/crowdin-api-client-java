package crowdin.api.client;

import crowdin.api.client.core.CrowdinApi;
import crowdin.api.client.core.http.HttpClient;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.model.Credentials;
import crowdin.api.client.projectsgroups.ProjectsGroupsApi;
import lombok.Getter;

@Getter
public class Client extends CrowdinApi {

    private final ProjectsGroupsApi projectsGroupsApi;

    public Client(Credentials credentials) {
        super(credentials);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials);
    }

    public Client(Credentials credentials, JsonTransformer jsonTransformer) {
        super(credentials, jsonTransformer);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, jsonTransformer);
    }

    public Client(Credentials credentials, HttpClient httpClient) {
        super(credentials, httpClient);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, httpClient);
    }
}
