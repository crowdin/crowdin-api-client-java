package com.crowdin.client;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.glossaries.GlossariesApi;
import com.crowdin.client.issues.IssuesApi;
import com.crowdin.client.languages.LanguagesApi;
import com.crowdin.client.machinetranslationengines.MachineTranslationEnginesApi;
import com.crowdin.client.projectsgroups.ProjectsGroupsApi;
import com.crowdin.client.reports.ReportsApi;
import com.crowdin.client.screenshots.ScreenshotsApi;
import com.crowdin.client.sourcefiles.SourceFilesApi;
import com.crowdin.client.sourcestrings.SourceStringsApi;
import com.crowdin.client.storage.StorageApi;
import com.crowdin.client.stringtranslations.StringTranslationsApi;
import com.crowdin.client.tasks.TasksApi;
import com.crowdin.client.translationmemory.TranslationMemoryApi;
import com.crowdin.client.translations.TranslationsApi;
import com.crowdin.client.translationstatus.TranslationStatusApi;
import com.crowdin.client.users.UsersApi;
import com.crowdin.client.vendors.VendorsApi;
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
    private final SourceStringsApi sourceStringsApi;
    private final StringTranslationsApi stringTranslationsApi;
    private final ScreenshotsApi screenshotsApi;
    private final GlossariesApi glossariesApi;
    private final TranslationMemoryApi translationMemoryApi;
    private final MachineTranslationEnginesApi machineTranslationEnginesApi;
    private final TranslationStatusApi translationStatusApi;
    private final ReportsApi reportsApi;
    private final TasksApi tasksApi;
    private final IssuesApi issuesApi;
    private final UsersApi usersApi;
    private final VendorsApi vendorsApi;

    public Client(Credentials credentials) {
        super(credentials);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials);
        this.storageApi = new StorageApi(credentials);
        this.languagesApi = new LanguagesApi(credentials);
        this.workflowsApi = new WorkflowsApi(credentials);
        this.sourceFilesApi = new SourceFilesApi(credentials);
        this.translationsApi = new TranslationsApi(credentials);
        this.sourceStringsApi = new SourceStringsApi(credentials);
        this.stringTranslationsApi = new StringTranslationsApi(credentials);
        this.screenshotsApi = new ScreenshotsApi(credentials);
        this.glossariesApi = new GlossariesApi(credentials);
        this.translationMemoryApi = new TranslationMemoryApi(credentials);
        this.machineTranslationEnginesApi = new MachineTranslationEnginesApi(credentials);
        this.translationStatusApi = new TranslationStatusApi(credentials);
        this.reportsApi = new ReportsApi(credentials);
        this.tasksApi = new TasksApi(credentials);
        this.issuesApi = new IssuesApi(credentials);
        this.usersApi = new UsersApi(credentials);
        this.vendorsApi = new VendorsApi(credentials);
    }

    public Client(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
        this.projectsGroupsApi = new ProjectsGroupsApi(credentials, clientConfig);
        this.storageApi = new StorageApi(credentials, clientConfig);
        this.languagesApi = new LanguagesApi(credentials, clientConfig);
        this.workflowsApi = new WorkflowsApi(credentials, clientConfig);
        this.sourceFilesApi = new SourceFilesApi(credentials, clientConfig);
        this.translationsApi = new TranslationsApi(credentials, clientConfig);
        this.sourceStringsApi = new SourceStringsApi(credentials, clientConfig);
        this.stringTranslationsApi = new StringTranslationsApi(credentials, clientConfig);
        this.screenshotsApi = new ScreenshotsApi(credentials, clientConfig);
        this.glossariesApi = new GlossariesApi(credentials, clientConfig);
        this.translationMemoryApi = new TranslationMemoryApi(credentials, clientConfig);
        this.machineTranslationEnginesApi = new MachineTranslationEnginesApi(credentials, clientConfig);
        this.translationStatusApi = new TranslationStatusApi(credentials, clientConfig);
        this.reportsApi = new ReportsApi(credentials, clientConfig);
        this.tasksApi = new TasksApi(credentials, clientConfig);
        this.issuesApi = new IssuesApi(credentials, clientConfig);
        this.usersApi = new UsersApi(credentials, clientConfig);
        this.vendorsApi = new VendorsApi(credentials, clientConfig);
    }

}
