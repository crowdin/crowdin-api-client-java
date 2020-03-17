package com.crowdin.client;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpClient;
import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.projectsgroups.ProjectsGroupsApi;
import com.crowdin.client.storage.StorageApi;
import lombok.Getter;

@Getter
public class Client extends CrowdinApi {

    private final ProjectsGroupsApi projectsGroupsApi;
    private final StorageApi storageApi;

    public Client(Credentials credentials) {
        super(credentials);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials);
        this.storageApi = new StorageApi(credentials);
    }

    public Client(Credentials credentials, JsonTransformer jsonTransformer) {
        super(credentials, jsonTransformer);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, jsonTransformer);
        this.storageApi = new StorageApi(credentials, jsonTransformer);
    }

    public Client(Credentials credentials, HttpClient httpClient) {
        super(credentials, httpClient);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, httpClient);
        this.storageApi = new StorageApi(credentials, httpClient);
    }
}
