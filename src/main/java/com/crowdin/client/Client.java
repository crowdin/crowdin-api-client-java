package com.crowdin.client;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.languages.LanguagesApi;
import com.crowdin.client.projectsgroups.ProjectsGroupsApi;
import com.crowdin.client.sourcefiles.SourceFilesApi;
import com.crowdin.client.storage.StorageApi;
import com.crowdin.client.translations.TranslationsApi;
import com.crowdin.client.workflows.WorkflowsApi;
import lombok.Getter;

@Getter
public class Client extends CrowdinApi {

    private final ProjectsGroupsApi projectsGroupsApi;
    private final StorageApi storageApi;
    private final LanguagesApi languagesApi;
    private final WorkflowsApi workflowsApi;
    private final SourceFilesApi sourceFilesApi;
    private final TranslationsApi translationsApi;

    public Client(Credentials credentials) {
        super(credentials);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials);
        this.storageApi = new StorageApi(credentials);
        this.languagesApi = new LanguagesApi(credentials);
        this.workflowsApi = new WorkflowsApi(credentials);
        this.sourceFilesApi = new SourceFilesApi(credentials);
        this.translationsApi = new TranslationsApi(credentials);
    }

    public Client(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, clientConfig);
        this.storageApi = new StorageApi(credentials, clientConfig);
        this.languagesApi = new LanguagesApi(credentials, clientConfig);
        this.workflowsApi = new WorkflowsApi(credentials, clientConfig);
        this.sourceFilesApi = new SourceFilesApi(credentials, clientConfig);
        this.translationsApi = new TranslationsApi(credentials, clientConfig);
    }

}
