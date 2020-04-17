package com.crowdin.client.reports;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.Format;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.reports.model.CostEstimateGenerateReportRequest;
import com.crowdin.client.reports.model.Currency;
import com.crowdin.client.reports.model.ReportStatus;
import com.crowdin.client.reports.model.Unit;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportsApiTest extends TestClient {

    private final Long projectId = 4L;
    private final String id = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id, HttpGet.METHOD_NAME, "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json")
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
        schema.setFormat(Format.XLSX);
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
}
