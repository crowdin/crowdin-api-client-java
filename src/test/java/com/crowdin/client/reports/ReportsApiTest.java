package com.crowdin.client.reports;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.Group;
import com.crowdin.client.reports.model.*;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportsApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long reportSettingsTemplateId = 2L;

    private final String name = "my report template";
    private final String id = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id, HttpGet.METHOD_NAME, "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpGet.METHOD_NAME, "api/reports/listReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpGet.METHOD_NAME, "api/reports/reportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpPatch.METHOD_NAME, "api/reports/editReportSettingsTemplate.json", "api/reports/reportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/settings-templates/" + reportSettingsTemplateId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addReportSettingsTemplate.json", "api/reports/reportSettingsTemplate.json")
                );
    }

    @Test
    public void generateReportTest() {
        CostEstimateGenerateReportRequest request = new CostEstimateGenerateReportRequest();
        request.setName("costs-estimation");
        CostEstimateGenerateReportRequest.Schema schema = new CostEstimateGenerateReportRequest.Schema();
        schema.setUnit(Unit.WORDS);
        schema.setCurrency(Currency.USD);
        schema.setLanguageId("ach");
        schema.setFormat(ReportsFormat.XLSX);
        CostEstimateGenerateReportRequest.TranslateStep translateStep = new CostEstimateGenerateReportRequest.TranslateStep();
        translateStep.setMode("simple");
        translateStep.setType("Translate");
        CostEstimateGenerateReportRequest.TranslateRegularRate regularRate = new CostEstimateGenerateReportRequest.TranslateRegularRate();
        regularRate.setMode(CostEstimateGenerateReportRequest.Mode.TM_MATCH);
        regularRate.setValue(0.1);
        translateStep.setRegularRates(Collections.singletonList(regularRate));
        CostEstimateGenerateReportRequest.TranslateIndividualRate individualRate = new CostEstimateGenerateReportRequest.TranslateIndividualRate();
        individualRate.setLanguageIdsTo(Collections.singletonList("uk"));
        individualRate.setRates(Collections.singletonList(regularRate));
        translateStep.setIndividualRates(Collections.singletonList(individualRate));
        schema.setStepTypes(Collections.singletonList(translateStep));
        request.setSchema(schema);
        ResponseObject<ReportStatus> reportStatusResponseObject = this.getReportsApi().generateReport(projectId, request);
        assertEquals(reportStatusResponseObject.getData().getIdentifier(), id);
    }

    @Test
    public void checkReportGenerationStatusTest() {
        ResponseObject<ReportStatus> reportStatusResponseObject = this.getReportsApi().checkReportGenerationStatus(projectId, id);
        assertEquals(reportStatusResponseObject.getData().getIdentifier(), id);
    }

    @Test
    public void downloadReportTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getReportsApi().downloadReport(projectId, id);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void listReportSettingsTemplateTest() {
        ResponseList<ReportSettingsTemplate> reportSettingsTemplateResponseList = this.getReportsApi().listReportSettingsTemplate(projectId, null, null);
        assertEquals(reportSettingsTemplateResponseList.getData().size(), 1);
        assertEquals(reportSettingsTemplateResponseList.getData().get(0).getData().getId(), projectId);
        assertEquals(reportSettingsTemplateResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addReportSettingsTemplateTest() {
        ReportSettingsTemplateRequest request = new ReportSettingsTemplateRequest();
        request.setName(name);
        request.setCurrency(Currency.USD);
        request.setUnit(Unit.WORDS);
        request.setMode("simple");

        ReportSettingsTemplateRequest.Config config = new ReportSettingsTemplateRequest.Config();
        ReportSettingsTemplateRequest.ProofreadRegularRate regularRate = new ReportSettingsTemplateRequest.ProofreadRegularRate();
        regularRate.setMode(ReportSettingsTemplateRequest.Mode.TM_MATCH);
        regularRate.setValue(0.1);

        ReportSettingsTemplateRequest.ProofreadIndividualRate individualRate = new ReportSettingsTemplateRequest.ProofreadIndividualRate();
        individualRate.setLanguageIds(Collections.singletonList("uk"));
        individualRate.setUserIds(Collections.singletonList(1));
        individualRate.setRates(Collections.singletonList(regularRate));

        config.setRegularRates(Collections.singletonList(regularRate));
        config.setIndividualRates(Collections.singletonList(individualRate));

        ResponseObject<ReportSettingsTemplate> responseObject = this.getReportsApi().addReportSettingsTemplate(projectId, request);
        assertEquals(responseObject.getData().getId(), projectId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void getReportSettingsTemplateTest() {
        ResponseObject<ReportSettingsTemplate> responseObject = this.getReportsApi().getReportSettingsTemplate(projectId, reportSettingsTemplateId);
        assertEquals(responseObject.getData().getId(), projectId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void editReportSettingsTemplateTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("name");
        ResponseObject<ReportSettingsTemplate> responseObject = this.getReportsApi().editReportSettingsTemplate(projectId, reportSettingsTemplateId, singletonList(request));
        assertEquals(responseObject.getData().getId(), projectId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void deleteReportSettingsTemplateTest() {
        this.getReportsApi().deleteReportSettingsTemplate(projectId, reportSettingsTemplateId);
    }
}
