package com.crowdin.client;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.projectsgroups.ProjectsGroupsApi;
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
