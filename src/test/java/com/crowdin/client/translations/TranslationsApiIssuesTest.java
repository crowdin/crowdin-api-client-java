package com.crowdin.client.translations;

import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translations.model.Method;
import com.crowdin.client.translations.model.PreTranslationReportResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TranslationsApiIssuesTest extends TestClient {

    private final Long projectId = 12L;
    private final Long parallelProjectId = 13L;
    private final String language = "uk";
    private final String preTranslationId = "9e7de270-4f83-41cb-b606-2f90631f26e2";
    private final long directoryId = 3L;
    private final Long fileId = 2L;
    private final Long branchId = 211L;
    private final Long storageId = 14L;
    private final Long buildId = 2L;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId + "/report", HttpGet.METHOD_NAME, "api/translations/preTranslationReportResponse_issueEmptyArray.json")
        );
    }

    @Test
    public void getPreTranslationReportTest() {
        ResponseObject<PreTranslationReportResponse> response = this.getTranslationsApi().getPreTranslationReport(projectId, preTranslationId);

        PreTranslationReportResponse report = response.getData();
        assertNotNull(report);
        assertEquals(Method.AI, report.getPreTranslateType());

        PreTranslationReportResponse.TargetLanguage lang = report.getLanguages().get(0);
        assertEquals(language, lang.getId());

        PreTranslationReportResponse.File file = lang.getFiles().get(0);
        assertEquals(fileId, file.getId());

        assertNull(lang.getSkipped());
        assertNull(lang.getSkippedQaCheckCategories());
    }
}