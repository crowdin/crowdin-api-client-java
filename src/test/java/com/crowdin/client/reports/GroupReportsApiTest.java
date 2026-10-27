package com.crowdin.client.reports;

import com.crowdin.client.core.http.impl.json.DateDeserializer;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.reports.model.*;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GroupReportsApiTest extends TestClient {
    private final Long groupId = 1L;
    private final String reportId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupReportRequest.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupTimeSpentReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupTaskUsageReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupTranslationCostsByTaskReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupPreTranslateAccuracyReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupPreTranslateAccuracyByTaskReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupTranslatorAccuracyReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupSavingActivityReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports/" + reportId, HttpGet.METHOD_NAME, "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports/" + reportId + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json"),
                RequestMock.build(this.url + "/reports", HttpPost.METHOD_NAME, "api/reports/generateOrganizationReportRequest.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupSourceContentUpdatesReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupQaCheckIssuesReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/reports", HttpPost.METHOD_NAME, "api/reports/generateGroupTranslationActivityReport.json", "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/reports/" + reportId, HttpGet.METHOD_NAME, "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/reports/" + reportId + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json")
        );
    }

    @Test
    public void generateGroupReport_TranslationCostsPostEditing() {
        GenerateGroupReportRequest request = new GenerateGroupReportRequest();
        request.setName("group-translation-costs-pe");
        request.setSchema(
                new GenerateGroupReportRequest.GroupTranslationCostsPostEditingSchema(){{
                    setProjectIds(Arrays.asList(1L, 2L, 3L));
                    setUnit(Unit.WORDS);
                    setCurrency(Currency.USD);
                    setFormat(ReportsFormat.XLSX);
                    setBaseRates(new BaseRatesForm() {{
                        setFullTranslation(0.1f);
                        setProofread(0.12f);
                    }});
                    setIndividualRates(Collections.singletonList(new IndividualRate() {{
                        setLanguageIds(Collections.singletonList("uk"));
                        setUserIds(Collections.singletonList(1L));
                        setFullTranslation(0.1f);
                        setProofread(0.12f);
                    }}));
                    setNetRateSchemes(new NetRateSchemes() {{
                        setTmMatch(Collections.singletonList(new Match() {{
                            setMatchType(MatchType.PERFECT);
                            setPrice(0.1f);
                        }}));
                        setMtMatch(Collections.singletonList(new Match() {{
                            setMatchType(MatchType.OPTION_99_82);
                            setPrice(0.1f);
                        }}));
                        setSuggestionMatch(Collections.singletonList(new Match() {{
                            setMatchType(MatchType.OPTION_81_60);
                            setPrice(0.1f);
                        }}));
                    }});
                    setUseCategoryBasedProofreadRates(true);
                    setUseTmEditDistance(false);
                    setGroupBy(GroupingParameter.PROJECT);
                    setUserIds(Collections.singletonList(13L));
                    setSkipArchiving(true);
                }}
        );

        ResponseObject<GroupReportStatus> response = this.getReportsApi().generateGroupReport(groupId, request);
        assertGroupReportStatus(response.getData());
    }

    @Test
    public void checkGroupReportGenerationStatus() {
        ResponseObject<GroupReportStatus> response = this.getReportsApi().checkGroupReportGenerationStatus(groupId, reportId);
        assertGroupReportStatus(response.getData());
    }

    @Test
    public void downloadGroupReport() {
        ResponseObject<DownloadLink> response = this.getReportsApi().downloadGroupReport(groupId, reportId);
        assertDownloadLink(response.getData());
    }

    @Test
    public void generateOrganizationReport() {
        GenerateGroupReportRequest request = new GenerateGroupReportRequest();
        request.setName("group-top-members");
        GenerateGroupReportRequest.GroupTopMembersSchema schema = new GenerateGroupReportRequest.GroupTopMembersSchema(){{
            setProjectIds(Arrays.asList(1L, 2L, 3L));
            setUnit(Unit.CHARS_WITH_SPACES);
            setLanguageId("uk");
            setFormat(ReportsFormat.JSON);
            setUserIds(Collections.singletonList(5L));
        }};
        request.setSchema(schema);

        ResponseObject<GroupReportStatus> responseObject = this.getReportsApi().generateOrganizationReport(request);
        assertGroupReportStatus(responseObject.getData());
    }

    @Test
    public void checkOrganizationReportGenerationStatus() {
        ResponseObject<GroupReportStatus> responseObject = this.getReportsApi().checkOrganizationReportGenerationStatus(reportId);
        assertGroupReportStatus(responseObject.getData());
    }

    @Test
    public void downloadOrganizationReport() {
        ResponseObject<DownloadLink> responseObject = this.getReportsApi().downloadOrganizationReport(reportId);
        assertDownloadLink(responseObject.getData());
    }

    @Test
    public void generateGroupReport_TimeSpent() {
        GenerateGroupReportRequest.GroupTimeSpentSchema schema = new GenerateGroupReportRequest.GroupTimeSpentSchema();
        schema.setFormat(ReportsFormat.XLSX);
        schema.setGroupBy("project");
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setHourly(10.5f);
        schema.setBaseRates(baseRates);
        IndividualRate individualRate = new IndividualRate();
        individualRate.setLanguageIds(Collections.singletonList("uk"));
        individualRate.setUserIds(Collections.singletonList(1L));
        individualRate.setHourly(12.5f);
        schema.setIndividualRates(Collections.singletonList(individualRate));
        schema.setLanguageId("uk");
        schema.setUserIds(Collections.singletonList(1L));
        schema.setTypeTasks(2);
        schema.setProjectIds(Arrays.asList(1L, 2L));
        schema.setTaskIds(Collections.singletonList(7L));
        schema.setSkipArchiving(true);

        assertGroupReportStatus(generateGroupReport("group-time-spent", schema));
    }

    @Test
    public void generateGroupReport_TaskUsage() {
        GenerateGroupReportRequest.GroupTaskUsageWorkloadSchema schema = new GenerateGroupReportRequest.GroupTaskUsageWorkloadSchema();
        schema.setFormat(ReportsFormat.CSV);
        schema.setProjectIds(Collections.singletonList(1L));
        schema.setGroupBy("project");
        schema.setTypeTasks(0);
        schema.setLanguageId("uk");
        schema.setCreatorId(2L);
        schema.setAssigneeId(3L);

        assertEquals("workload", schema.getType());
        assertGroupReportStatus(generateGroupReport("group-task-usage", schema));
    }

    @Test
    public void generateGroupReport_TranslationCostsPostEditingByTask() {
        GenerateGroupReportRequest.GroupTranslationCostsPostEditingByTaskSchema schema = new GenerateGroupReportRequest.GroupTranslationCostsPostEditingByTaskSchema();
        schema.setUnit(Unit.WORDS);
        schema.setCurrency(Currency.EUR);
        schema.setFormat(ReportsFormat.XLSX);
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setFullTranslation(0.1f);
        schema.setBaseRates(baseRates);
        NetRateSchemes netRateSchemes = new NetRateSchemes();
        Match match = new Match();
        match.setMatchType(MatchType.PERFECT);
        match.setPrice(0.1f);
        netRateSchemes.setTmMatch(Collections.singletonList(match));
        schema.setNetRateSchemes(netRateSchemes);
        schema.setTaskIds(Arrays.asList(7L, 8L));
        schema.setUseCategoryBasedProofreadRates(false);
        schema.setUseTmEditDistance(true);
        schema.setSkipArchiving(true);

        assertGroupReportStatus(generateGroupReport("group-translation-costs-pe", schema));
    }

    @Test
    public void generateGroupReport_PreTranslateAccuracy() {
        GenerateGroupReportRequest.GroupPreTranslateAccuracySchema schema = new GenerateGroupReportRequest.GroupPreTranslateAccuracySchema();
        schema.setUnit(Unit.WORDS);
        schema.setLanguageId("uk");
        schema.setFormat(ReportsFormat.XLSX);
        schema.setMatchScoreCategories(Collections.singletonList("100-90"));
        schema.setProjectIds(Arrays.asList(1L, 2L));
        schema.setSkipArchiving(false);

        assertGroupReportStatus(generateGroupReport("group-pre-translate-accuracy", schema));
    }

    @Test
    public void generateGroupReport_PreTranslateAccuracyByTask() {
        GenerateGroupReportRequest.GroupPreTranslateAccuracyByTaskSchema schema = new GenerateGroupReportRequest.GroupPreTranslateAccuracyByTaskSchema();
        schema.setUnit(Unit.STRINGS);
        schema.setFormat(ReportsFormat.JSON);
        schema.setMatchScoreCategories(Collections.singletonList("100-90"));
        schema.setTaskIds(Collections.singletonList(7L));

        assertGroupReportStatus(generateGroupReport("group-pre-translate-accuracy", schema));
    }

    @Test
    public void generateGroupReport_TranslatorAccuracy() {
        GenerateGroupReportRequest.GroupTranslatorAccuracySchema schema = new GenerateGroupReportRequest.GroupTranslatorAccuracySchema();
        schema.setUnit(Unit.WORDS);
        schema.setFormat(ReportsFormat.XLSX);
        schema.setMatchScoreCategories(Collections.singletonList("100-90"));
        schema.setLanguageId("uk");
        schema.setUserIds(Collections.singletonList(1L));
        schema.setProjectIds(Collections.singletonList(1L));
        schema.setSkipArchiving(true);

        assertGroupReportStatus(generateGroupReport("group-translator-accuracy", schema));
    }

    @Test
    public void generateGroupReport_SavingActivity() {
        GenerateGroupReportRequest.GroupSavingActivitySchema schema = new GenerateGroupReportRequest.GroupSavingActivitySchema();
        schema.setUnit(Unit.CHARS);
        schema.setProjectIds(Collections.singletonList(1L));
        schema.setFormat(ReportsFormat.XLSX);
        schema.setLanguageId("uk");
        schema.setMode("currency");

        assertGroupReportStatus(generateGroupReport("group-saving-activity", schema));
    }

    @Test
    public void generateOrganizationReport_SourceContentUpdates() {
        GenerateGroupReportRequest.GroupSourceContentUpdatesSchema schema = new GenerateGroupReportRequest.GroupSourceContentUpdatesSchema();
        schema.setUnit(Unit.WORDS);
        schema.setFormat(ReportsFormat.CSV);
        schema.setProjectIds(Arrays.asList(1L, 2L));

        assertGroupReportStatus(generateOrganizationReport("group-source-content-updates", schema));
    }

    @Test
    public void generateOrganizationReport_QaCheckIssues() {
        GenerateGroupReportRequest.GroupQaCheckIssuesSchema schema = new GenerateGroupReportRequest.GroupQaCheckIssuesSchema();
        schema.setProjectIds(Collections.singletonList(1L));
        schema.setFormat(ReportsFormat.XLSX);

        assertGroupReportStatus(generateOrganizationReport("group-qa-check-issues", schema));
    }

    @Test
    public void generateOrganizationReport_TranslationActivity() {
        GenerateGroupReportRequest.GroupTranslationActivitySchema schema = new GenerateGroupReportRequest.GroupTranslationActivitySchema();
        schema.setUnit(Unit.WORDS);
        schema.setProjectIds(Collections.singletonList(1L));
        schema.setFormat(ReportsFormat.JSON);
        schema.setUserIds(Collections.singletonList(4L));

        assertGroupReportStatus(generateOrganizationReport("group-translation-activity", schema));
    }

    private GroupReportStatus generateGroupReport(String name, GenerateGroupReportRequest.Schema schema) {
        GenerateGroupReportRequest request = new GenerateGroupReportRequest();
        request.setName(name);
        request.setSchema(schema);
        return this.getReportsApi().generateGroupReport(groupId, request).getData();
    }

    private GroupReportStatus generateOrganizationReport(String name, GenerateGroupReportRequest.Schema schema) {
        GenerateGroupReportRequest request = new GenerateGroupReportRequest();
        request.setName(name);
        request.setSchema(schema);
        return this.getReportsApi().generateOrganizationReport(request).getData();
    }

    @SneakyThrows
    private void assertGroupReportStatus(GroupReportStatus reportStatus) {
        assertNotNull(reportStatus);

        assertEquals(reportId, reportStatus.getIdentifier());
        assertEquals("finished", reportStatus.getStatus());
        assertEquals(100, reportStatus.getProgress());

        GroupReportStatus.Attributes attributes = reportStatus.getAttributes();
        assertNotNull(attributes);
        assertEquals(ReportsFormat.XLSX, attributes.getFormat());
        assertEquals("costs-estimation", attributes.getReportName());
        assertEquals(Collections.singletonList(0L), attributes.getProjectIds());

        assertEquals(DateDeserializer.deserializeDate("2019-09-23T11:26:54+00:00"), reportStatus.getCreatedAt());
        assertEquals(DateDeserializer.deserializeDate("2019-09-23T11:26:54+00:00"), reportStatus.getUpdatedAt());
        assertEquals(DateDeserializer.deserializeDate("2019-09-23T11:26:54+00:00"), reportStatus.getStartedAt());
        assertEquals(DateDeserializer.deserializeDate("2019-09-23T11:26:54+00:00"), reportStatus.getFinishedAt());
    }

    private void assertDownloadLink(DownloadLink downloadLink) {
        assertNotNull(downloadLink);
        assertEquals("test.com", downloadLink.getUrl());
        assertNotNull(downloadLink.getExpireIn());
    }
}
