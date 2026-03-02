package com.crowdin.client.translationstatus;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translationstatus.model.FileBranchProgress;
import com.crowdin.client.translationstatus.model.LanguageProgress;
import com.crowdin.client.translationstatus.model.QaCheck;
import com.crowdin.client.translationstatus.model.QaCheckRevalidation;
import com.crowdin.client.translationstatus.model.QaCheckRevalidationRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationStatusApiTest extends TestClient {

    private final Long projectId = 2L;
    private final String revalidationId = "b5215a34-1305-4b21-8054-fc2eb252842f";
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
                RequestMock.build(this.url + "/projects/" + projectId + "/qa-checks", HttpGet.METHOD_NAME, "api/translationstatus/listQaChecks.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/qa-checks/revalidate/" + revalidationId, HttpGet.METHOD_NAME, "api/translationstatus/qaChecksRevalidation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/qa-checks/revalidate", HttpPost.METHOD_NAME, "api/translationstatus/qaChecksRevalidationRequest.json","api/translationstatus/qaChecksRevalidation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/qa-checks/revalidate/" + revalidationId, HttpDelete.METHOD_NAME)
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
        ResponseList<FileBranchProgress> languageProgress = this.getTranslationStatusApi().getLanguageProgress(projectId, languageId, null, null);
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

    @Test
    public void getQaChecksRevalidationStatusTest() {
        ResponseObject<QaCheckRevalidation> qaCheckRevalidationResponseObject = this.getTranslationStatusApi().getQaChecksRevalidationStatus(projectId, revalidationId);
        assertEquals(qaCheckRevalidationResponseObject.getData().getStatus(), "created");
        assertEquals(qaCheckRevalidationResponseObject.getData().getProgress(), 0L);
        assertEquals(qaCheckRevalidationResponseObject.getData().getAttributes().getLanguageIds(), Arrays.asList("uk", "fr"));
        assertEquals(qaCheckRevalidationResponseObject.getData().getAttributes().getFailedOnly(), false);
        Date createdDate = new Date(2025 - 1900, Calendar.SEPTEMBER, 23, 11, 51, 8);
        assertEquals(qaCheckRevalidationResponseObject.getData().getCreatedAt(), createdDate);
    }

    @Test
    public void revalidateQaChecksTest() {
        QaCheckRevalidationRequest request = new QaCheckRevalidationRequest();
        request.setLanguageIds(Arrays.asList("uk", "fr"));
        request.setQaCheckCategories(Collections.singletonList("ai"));
        request.setFailedOnly(true);
        ResponseObject<QaCheckRevalidation> qaCheckRevalidationResponseObject = this.getTranslationStatusApi().revalidateQaChecks(projectId, request);
        assertEquals(qaCheckRevalidationResponseObject.getData().getIdentifier(), revalidationId);
        assertEquals(qaCheckRevalidationResponseObject.getData().getStatus(), "created");
        assertEquals(qaCheckRevalidationResponseObject.getData().getProgress(), 0L);
        assertEquals(qaCheckRevalidationResponseObject.getData().getAttributes().getLanguageIds(), Arrays.asList("uk", "fr"));
        assertEquals(qaCheckRevalidationResponseObject.getData().getAttributes().getQaCheckCategories(), Collections.singletonList("ai"));
        assertEquals(qaCheckRevalidationResponseObject.getData().getAttributes().getFailedOnly(), false);
        Date createdDate = new Date(2025 - 1900, Calendar.SEPTEMBER, 23, 11, 51, 8);
        assertEquals(qaCheckRevalidationResponseObject.getData().getCreatedAt(), createdDate);
    }

    @Test
    public void cancelQaChecksRevalidationTest() {
        this.getTranslationStatusApi().cancelQaChecksRevalidation(projectId, revalidationId);
    }
}
