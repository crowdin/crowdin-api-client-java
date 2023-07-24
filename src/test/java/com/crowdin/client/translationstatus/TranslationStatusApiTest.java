package com.crowdin.client.translationstatus;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translationstatus.model.FileProgress;
import com.crowdin.client.translationstatus.model.LanguageProgress;
import com.crowdin.client.translationstatus.model.QaCheck;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationStatusApiTest extends TestClient {

    private final Long projectId = 2L;
    private final Long branchId = 3L;
    private final Long directoryId = 4L;
    private final Long fileId = 5L;
    private final String languageId = "uk";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + branchId + "/languages/progress", HttpGet.METHOD_NAME, "api/translationstatus/branchProgress.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories/" + directoryId + "/languages/progress", HttpGet.METHOD_NAME, "api/translationstatus/directoryProgress.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/languages/progress", HttpGet.METHOD_NAME, "api/translationstatus/fileProgress.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/languages/" + languageId + "/progress", HttpGet.METHOD_NAME, "api/translationstatus/languageProgress.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/languages/progress", HttpGet.METHOD_NAME, "api/translationstatus/projectProgress.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/qa-checks", HttpGet.METHOD_NAME, "api/translationstatus/listQaChecks.json")
        );
    }

    @Test
    public void getBranchProgressTest() {
        ResponseList<LanguageProgress> branchProgress = this.getTranslationStatusApi().getBranchProgress(projectId, branchId, null, null);
        assertEquals(branchProgress.getData().size(), 1);
        assertEquals(branchProgress.getData().get(0).getData().getPhrases().getTotal(), (Integer) 3000);
        assertEquals(branchProgress.getData().get(0).getData().getPhrases().getPreTranslateAppliedTo(), (Integer) 1254);
    }

    @Test
    public void getDirectoryProgressTest() {
        ResponseList<LanguageProgress> directoryProgress = this.getTranslationStatusApi().getDirectoryProgress(projectId, directoryId, null, null);
        assertEquals(directoryProgress.getData().size(), 1);
        assertEquals(directoryProgress.getData().get(0).getData().getPhrases().getTotal(), (Integer) 2000);
        assertEquals(directoryProgress.getData().get(0).getData().getPhrases().getPreTranslateAppliedTo(), (Integer) 1254);
    }

    @Test
    public void getFileProgressTest() {
        ResponseList<LanguageProgress> fileProgress = this.getTranslationStatusApi().getFileProgress(projectId, fileId, null, null);
        assertEquals(fileProgress.getData().size(), 1);
        assertEquals(fileProgress.getData().get(0).getData().getPhrases().getTotal(), (Integer) 4000);
        assertEquals(fileProgress.getData().get(0).getData().getPhrases().getPreTranslateAppliedTo(), (Integer) 1254);
    }

    @Test
    public void getLanguageProgressTest() {
        ResponseList<FileProgress> languageProgress = this.getTranslationStatusApi().getLanguageProgress(projectId, languageId, null, null);
        assertEquals(languageProgress.getData().size(), 1);
        assertEquals(languageProgress.getData().get(0).getData().getPhrases().getTotal(), (Integer) 5000);
        assertEquals(languageProgress.getData().get(0).getData().getPhrases().getPreTranslateAppliedTo(), (Integer) 1254);
    }

    @Test
    public void getProjectProgressTest() {
        ResponseList<LanguageProgress> projectProgress = this.getTranslationStatusApi().getProjectProgress(projectId, null, null, null);
        assertEquals(projectProgress.getData().size(), 1);
        assertEquals(projectProgress.getData().get(0).getData().getPhrases().getTotal(), (Integer) 6000);
        assertEquals(projectProgress.getData().get(0).getData().getPhrases().getPreTranslateAppliedTo(), (Integer) 1254);
    }

    @Test
    public void listQaCheckIssuesTest() {
        ResponseList<QaCheck> qaCheckResponseList = this.getTranslationStatusApi().listQaCheckIssues(projectId, null, null, null, null);
        assertEquals(qaCheckResponseList.getData().size(), 1);
        assertEquals(qaCheckResponseList.getData().get(0).getData().getLanguageId(), languageId);
    }
}
