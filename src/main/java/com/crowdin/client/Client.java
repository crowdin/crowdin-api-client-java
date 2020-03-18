package com.crowdin.client;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.model.ClientConfig;
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

    public Client(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, clientConfig);
        this.storageApi = new StorageApi(credentials, clientConfig);
    }

}
