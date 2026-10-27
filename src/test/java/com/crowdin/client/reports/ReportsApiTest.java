package com.crowdin.client.reports;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.reports.model.*;
import com.crowdin.client.reports.model.Currency;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ReportsApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long reportSettingsTemplateId = 2L;
    private final String languageId = "ach";
    private final String engLanguageId = "uk";
    private final String postEditingCategory = "0-10";
    private final float fullTranslationRate = 0.1F;
    private final float proofreadRate = 0.12F;
    private final long fileId = 138L;
    private final long directoryId = 11L;
    private final long branchId = 18L;
    private final long labelId = 13L;

    private final String name = "my report template";
    private final String id = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String link = "test.com";
    private final TimeZone tz = TimeZone.getTimeZone("GMT");
    private final Calendar calendar = GregorianCalendar.getInstance(tz);

    // -- REPORT ARCHIVE --//

    private final Long archiveId = 1L;
    private final String scopeType = "project";
    private final Long scopeId = 1L;
    private final Long userId = 1L;
    private final String archiveName = "my archive report";
    private final String webUrl = "https://crowdin.com/project/project-identifier/reports/archive/1";

    private final String exportId = "1";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generatePreTranslateAccuracyReport.json", "api/reports/preTranslateAccuracyReportStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTimeSpentReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTaskUsageReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTaskUsageTimeReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTranslationCostsByTaskReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateCostsEstimationByTaskReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTranslatorAccuracyReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generatePreTranslateAccuracyNewFieldsReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateSavingActivityReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTopMembersReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports", HttpPost.METHOD_NAME, "api/reports/generateTranslationActivityReport.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addHourlyReportSettingsTemplate.json", "api/reports/hourlyReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addPostEditingReportSettingsTemplate.json", "api/reports/postEditingReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id, HttpGet.METHOD_NAME, "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/" + id + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json"),
                RequestMock.build(this.url + "/reports/settings-templates", HttpGet.METHOD_NAME, "api/reports/listOrganizationReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addReportSettingsTemplate.json", "api/reports/organizationReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/reports/settings-templates/" + reportSettingsTemplateId, HttpGet.METHOD_NAME, "api/reports/organizationReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/reports/settings-templates/" + reportSettingsTemplateId, HttpPatch.METHOD_NAME, "api/reports/editReportSettingsTemplate.json", "api/reports/organizationReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/reports/settings-templates/" + reportSettingsTemplateId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpGet.METHOD_NAME, "api/reports/listReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addReportSettingsTemplate.json", "api/reports/reportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpGet.METHOD_NAME, "api/reports/reportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpPatch.METHOD_NAME, "api/reports/editReportSettingsTemplate.json", "api/reports/reportSettingsTemplate.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives", HttpGet.METHOD_NAME, "api/reports/listReportArchives.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives", HttpGet.METHOD_NAME, "api/reports/listReportArchives.json", new HashMap<String, Object>() {{
                    put("scopeType", scopeType);
                    put("scopeId", scopeId);
                    put("userId", 5L);
                    put("taskId", 7L);
                    put("name", "my archive");
                    put("dateFrom", "2024-01-01T00:00:00+00:00");
                    put("dateTo", "2024-02-01T00:00:00+00:00");
                    put("limit", 10);
                    put("offset", 0);
                }}),
                RequestMock.build(this.url + "/reports/archives", HttpGet.METHOD_NAME, "api/reports/listReportArchives.json", new HashMap<String, Object>() {{
                    put("taskId", 7L);
                }}),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives/" + archiveId, HttpGet.METHOD_NAME, "api/reports/reportArchive.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives/" + archiveId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives/" + archiveId + "/exports", HttpPost.METHOD_NAME, "api/reports/exportReportArchiveReques.json", "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives/" + archiveId + "/exports/" + exportId, HttpGet.METHOD_NAME, "api/reports/reportGenerationStatus.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/archives/" + archiveId + "/exports/" + exportId + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/settings-templates", HttpGet.METHOD_NAME, "api/reports/listUserReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/settings-templates", HttpPost.METHOD_NAME, "api/reports/addUserReportSettingsTemplate.json", "api/reports/userReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpGet.METHOD_NAME, "api/reports/userReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpPatch.METHOD_NAME, "api/reports/editUserReportSettingsTemplate.json", "api/reports/userReportSettingsTemplate.json"),
                RequestMock.build(this.url + "/users/" + userId + "/reports/settings-templates/" + reportSettingsTemplateId, HttpDelete.METHOD_NAME));
    }

    private ReportSettingsTemplate createSettingsTemplate() {
        ReportSettingsTemplate.RegularRate regular = new ReportSettingsTemplate.RegularRate();
        regular.setMode(ReportSettingsTemplate.Mode.NO_MATCH);
        regular.setValue(0.1);

        ReportSettingsTemplate.RegularRate innerRate = new ReportSettingsTemplate.RegularRate();
        innerRate.setMode(ReportSettingsTemplate.Mode.TM_MATCH);
        innerRate.setValue(0.1);

        IndividualRate individual = new IndividualRate();
        individual.setLanguageIds(Collections.singletonList("uk"));
        individual.setUserIds(Collections.singletonList(20L));

        List<ReportSettingsTemplate.RegularRate> regularRates = Collections.singletonList(regular);
        List<IndividualRate> individualRates = Collections.singletonList(individual);

        ReportSettingsTemplate.Config config = new ReportSettingsTemplate.Config();
        config.setRegularRates(regularRates);
        config.setIndividualRates(individualRates);

        ReportSettingsTemplate template = new ReportSettingsTemplate();
        template.setName(name);
        template.setCurrency(Currency.USD);
        template.setUnit(Unit.STRINGS);
        template.setConfig(config);
        template.setIsPublic(false);

        return template;
    }

    private ReportSettingsTemplate.OrganizationReportSettingsTemplate createOrganizationSettingsTemplate() {
        ReportSettingsTemplate.RegularRate regular = new ReportSettingsTemplate.RegularRate();
        regular.setMode(ReportSettingsTemplate.Mode.NO_MATCH);
        regular.setValue(0.1);

        ReportSettingsTemplate.RegularRate innerRate = new ReportSettingsTemplate.RegularRate();
        innerRate.setMode(ReportSettingsTemplate.Mode.TM_MATCH);
        innerRate.setValue(0.1);

        IndividualRate individual = new IndividualRate();
        individual.setLanguageIds(Collections.singletonList("uk"));
        individual.setUserIds(Collections.singletonList(20L));

        List<ReportSettingsTemplate.RegularRate> regularRates = Collections.singletonList(regular);
        List<IndividualRate> individualRates = Collections.singletonList(individual);

        ReportSettingsTemplate.Config config = new ReportSettingsTemplate.Config();
        config.setRegularRates(regularRates);
        config.setIndividualRates(individualRates);

        ReportSettingsTemplate.OrganizationReportSettingsTemplate template = new ReportSettingsTemplate.OrganizationReportSettingsTemplate();
        template.setName(name);
        template.setCurrency(Currency.USD);
        template.setUnit(Unit.STRINGS);
        template.setConfig(config);
        template.setIsPublic(false);

        return template;
    }

    private Date getDate(int year, int month, int date, int hour, int minute, int second) {
        calendar.set(year, month, date, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Test
    public void generateReportTest() {
        TimeZone.setDefault(tz);
        Date reportCreatedAt = getDate(2019, Calendar.SEPTEMBER, 23, 11, 26, 54);
        CostEstimationPostEditingGenerateReportRequest request = new CostEstimationPostEditingGenerateReportRequest();
        CostEstimationPostEditingGenerateReportRequest.GeneralSchema schema = new CostEstimationPostEditingGenerateReportRequest.GeneralSchema();
        schema.setUnit(Unit.STRINGS);
        schema.setCurrency(Currency.USD);
        schema.setLanguageId(languageId);
        schema.setFormat(ReportsFormat.XLSX);
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setFullTranslation(fullTranslationRate);
        baseRates.setProofread(proofreadRate);
        IndividualRate individualRate = new IndividualRate();
        individualRate.setLanguageIds(singletonList(engLanguageId));
        individualRate.setFullTranslation(fullTranslationRate);
        individualRate.setProofread(proofreadRate);
        NetRateSchemes netRateSchemes = new NetRateSchemes();
        Match match = new Match();
        match.setMatchType(MatchType.PERFECT);
        match.setPrice(fullTranslationRate);
        netRateSchemes.setTmMatch(singletonList(match));
        schema.setBaseRates(baseRates);
        schema.setIndividualRates(singletonList(individualRate));
        schema.setNetRateSchemes(netRateSchemes);
        schema.setCalculateInternalMatches(false);
        schema.setIncludePreTranslatedStrings(false);
        schema.setFileIds(singletonList(fileId));
        schema.setDirectoryIds(singletonList(directoryId));
        schema.setBranchIds(singletonList(branchId));
        schema.setLabelIds(singletonList(labelId));
        schema.setLabelIncludeType(LabelIncludeType.STRINGS_WITH_LABEL);
        request.setSchema(schema);
        ResponseObject<ReportStatus> reportStatusResponseObject = this.getReportsApi().generateReport(projectId, request);
        assertEquals(reportStatusResponseObject.getData().getIdentifier(), id);
        assertEquals(reportCreatedAt, reportStatusResponseObject.getData().getCreatedAt());
    }

    @Test
    public void testGeneratePreTranslateAccuracyReport() {
        TimeZone.setDefault(tz);
        Date reportCreatedAt = getDate(2019, Calendar.SEPTEMBER, 23, 11, 26, 54);

        PreTranslateAccuracyGenerateReportRequest request = new PreTranslateAccuracyGenerateReportRequest();
        PreTranslateAccuracyGenerateReportRequest.GeneralSchema schema = new PreTranslateAccuracyGenerateReportRequest.GeneralSchema();
        schema.setUnit(Unit.STRINGS);
        schema.setPostEditingCategories(singletonList(postEditingCategory));
        schema.setLanguageId(languageId);
        request.setSchema(schema);
        request.setName("pre-translate-accuracy");

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        ReportStatus reportStatus = response.getData();
        ReportStatus.Attributes attributes = reportStatus.getAttributes();

        assertEquals(id, reportStatus.getIdentifier());
        assertEquals(ReportsFormat.XLSX, attributes.getFormat());
        assertEquals(request.getName(), attributes.getReportName());
        assertEquals(reportCreatedAt, reportStatus.getCreatedAt());
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
    public void listOrganizationReportSettingsTemplateTest() {
        ResponseList<ReportSettingsTemplate.OrganizationReportSettingsTemplate> reportSettingsTemplateResponseList = this.getReportsApi().listOrganizationReportSettingsTemplates(new ListOrganizationReportSettingsParams());
        assertEquals(reportSettingsTemplateResponseList.getData().size(), 1);
        assertEquals(reportSettingsTemplateResponseList.getData().get(0).getData().getId(), reportSettingsTemplateId);
        assertEquals(reportSettingsTemplateResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addOrganizationReportSettingsTemplateTest() {
        ReportSettingsTemplate.OrganizationReportSettingsTemplate request = createOrganizationSettingsTemplate();
        ResponseObject<ReportSettingsTemplate.OrganizationReportSettingsTemplate> reportSettingsTemplateResponseObject = this.getReportsApi().addOrganizationReportSettingsTemplate(request);
        ReportSettingsTemplate response = reportSettingsTemplateResponseObject.getData();
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCurrency(), response.getCurrency());
        assertEquals(request.getUnit(), response.getUnit());
        assertEquals(request.getConfig(), response.getConfig());
    }

    @Test
    public void getOrganizationReportSettingsTemplateTest() {
        ResponseObject<ReportSettingsTemplate.OrganizationReportSettingsTemplate> responseObject = this.getReportsApi().getOrganizationReportSettingsTemplate(reportSettingsTemplateId);
        assertEquals(responseObject.getData().getId(), reportSettingsTemplateId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void editOrganizationReportSettingsTemplateTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("name");
        ResponseObject<ReportSettingsTemplate.OrganizationReportSettingsTemplate> responseObject = this.getReportsApi().editOrganizationReportSettingsTemplate(reportSettingsTemplateId, singletonList(request));
        assertEquals(responseObject.getData().getId(), reportSettingsTemplateId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void deleteOrganizationReportSettingsTemplateTest() {
        this.getReportsApi().deleteOrganizationReportSettingsTemplate(reportSettingsTemplateId);
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
        ReportSettingsTemplate request = createSettingsTemplate();
        ResponseObject<ReportSettingsTemplate> reportSettingsTemplateResponseObject = this.getReportsApi().addReportSettingsTemplate(projectId, request);
        ReportSettingsTemplate response = reportSettingsTemplateResponseObject.getData();
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCurrency(), response.getCurrency());
        assertEquals(request.getUnit(), response.getUnit());
        assertEquals(request.getConfig(), response.getConfig());
        assertEquals(request.getIsPublic(), response.getIsPublic());
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

    @Test
    public void listUserReportSettingsTemplateTest() {
        ResponseList<ReportSettingsTemplate> userReportSettingsTemplateResponseList = this.getReportsApi().listUserReportSettingsTemplate(userId, null, null);
        assertEquals(userReportSettingsTemplateResponseList.getData().size(), 1);
        assertEquals(userReportSettingsTemplateResponseList.getData().get(0).getData().getId(), userId);
        assertEquals(userReportSettingsTemplateResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addUserReportSettingsTemplateTest() {
        ReportSettingsTemplate request = createSettingsTemplate();
        request.setIsPublic(null);
        ResponseObject<ReportSettingsTemplate> userReportSettingsTemplateResponseObject = this.getReportsApi().addUserReportSettingsTemplate(userId, request);
        ReportSettingsTemplate response = userReportSettingsTemplateResponseObject.getData();
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCurrency(), response.getCurrency());
        assertEquals(request.getUnit(), response.getUnit());
        assertEquals(request.getConfig(), response.getConfig());
    }

    @Test
    public void getUserReportSettingsTemplateTest() {
        ResponseObject<ReportSettingsTemplate> responseObject = this.getReportsApi().getUserReportSettingsTemplate(userId, reportSettingsTemplateId);
        assertEquals(responseObject.getData().getId(), userId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void editUserReportSettingsTemplateTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("name");
        ResponseObject<ReportSettingsTemplate> responseObject = this.getReportsApi().editUserReportSettingsTemplate(userId, reportSettingsTemplateId, singletonList(request));
        assertEquals(responseObject.getData().getId(), userId);
        assertEquals(responseObject.getData().getName(), name);
    }

    @Test
    public void deleteUserReportSettingsTemplateTest() {
        this.getReportsApi().deleteUserReportSettingsTemplate(userId, reportSettingsTemplateId);
    }

    @Test
    public void getListReportArchivesTest() {
        ResponseList<ReportArchive> responseObject = this.getReportsApi().listReportArchives(userId, null, null, null, null);
        assertEquals(responseObject.getData().size(), 1);
        assertEquals(responseObject.getData().get(0).getData().getId(), archiveId);
        assertEquals(responseObject.getData().get(0).getData().getScopeType(), scopeType);
        assertEquals(responseObject.getData().get(0).getData().getScopeId(), scopeId);
        assertEquals(responseObject.getData().get(0).getData().getName(), archiveName);
        assertEquals(responseObject.getData().get(0).getData().getWebUrl(), webUrl);
        assertEquals("finished", responseObject.getData().get(0).getData().getStatus());
        assertEquals(100, responseObject.getData().get(0).getData().getProgress());
    }

    @Test
    public void listReportArchivesWithParamsTest() {
        ListReportArchivesParams params = new ListReportArchivesParams();
        params.setScopeType(scopeType);
        params.setScopeId(scopeId);
        params.setUserId(5L);
        params.setTaskId(7L);
        params.setName("my archive");
        params.setDateFrom("2024-01-01T00:00:00+00:00");
        params.setDateTo("2024-02-01T00:00:00+00:00");
        params.setLimit(10);
        params.setOffset(0);
        ResponseList<ReportArchive> responseList = this.getReportsApi().listReportArchives(userId, params);
        assertEquals(1, responseList.getData().size());
        assertEquals(archiveId, responseList.getData().get(0).getData().getId());
    }

    @Test
    public void listEnterpriseReportArchivesWithParamsTest() {
        ListReportArchivesParams params = new ListReportArchivesParams();
        params.setTaskId(7L);
        ResponseList<ReportArchive> responseList = this.getReportsApi().listReportArchives(null, params);
        assertEquals(1, responseList.getData().size());
        assertEquals(scopeType, responseList.getData().get(0).getData().getScopeType());
    }

    @Test
    public void getReportArchivesTest() {
        ResponseObject<ReportArchive> reportArchive = this.getReportsApi().getReportArchive(userId, archiveId);
        assertEquals(reportArchive.getData().getWebUrl(), webUrl);
        assertEquals("finished", reportArchive.getData().getStatus());
        assertEquals(100, reportArchive.getData().getProgress());
    }

    @Test
    public void deleteReportArchivesTest() {
        try {
            this.getReportsApi().deleteReportArchive(userId, archiveId);
            // If no exception is thrown, the delete operation is successful
        } catch (HttpBadRequestException e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void exportReportArchiveTest() {
        ExportReportRequest request = new ExportReportRequest();
        request.setFormat(ReportsFormat.from("xlsx"));

        ResponseObject<GroupReportStatus> exportReportArchive = this.getReportsApi().exportReportArchive(userId, archiveId, request);
        assertEquals(exportReportArchive.getData().getAttributes().getFormat(), ReportsFormat.XLSX);
    }

    @Test
    public void checkReportArchiveExportStatusTest() {
        ResponseObject<GroupReportStatus> reportStatusResponseObject = this.getReportsApi().checkReportArchiveExportStatus(userId, archiveId, exportId);
        assertEquals(reportStatusResponseObject.getData().getIdentifier(), id);
    }

    @Test
    public void downloadReportArchiveTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getReportsApi().downloadReportArchive(userId, archiveId, exportId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void generateTimeSpentReportTest() {
        TimeSpentGenerateReportRequest request = new TimeSpentGenerateReportRequest();
        TimeSpentGenerateReportRequest.Schema schema = new TimeSpentGenerateReportRequest.Schema();
        schema.setFormat(ReportsFormat.XLSX);
        schema.setGroupBy("task");
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setHourly(10.5F);
        schema.setBaseRates(baseRates);
        IndividualRate individualRate = new IndividualRate();
        individualRate.setLanguageIds(singletonList(engLanguageId));
        individualRate.setUserIds(singletonList(userId));
        individualRate.setHourly(12.5F);
        schema.setIndividualRates(singletonList(individualRate));
        schema.setLanguageId(languageId);
        schema.setUserIds(singletonList(userId));
        schema.setTypeTasks(0);
        schema.setTaskIds(singletonList(7L));
        schema.setWorkflowStepId(3L);
        schema.setSkipArchiving(true);
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTaskUsageCostReportTest() {
        TaskUsageGenerateReportRequest request = new TaskUsageGenerateReportRequest();
        TaskUsageGenerateReportRequest.CostSchema schema = new TaskUsageGenerateReportRequest.CostSchema();
        schema.setFormat(ReportsFormat.JSON);
        schema.setGroupBy("language");
        schema.setTypeTasks(1);
        schema.setLanguageId(languageId);
        schema.setCreatorId(2L);
        schema.setAssigneeId(3L);
        schema.setStatuses(Arrays.asList("todo", "done"));
        request.setSchema(schema);

        assertEquals("cost", schema.getType());
        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTaskUsageTimeReportTest() {
        TaskUsageGenerateReportRequest request = new TaskUsageGenerateReportRequest();
        TaskUsageGenerateReportRequest.TimeSchema schema = new TaskUsageGenerateReportRequest.TimeSchema();
        schema.setFormat(ReportsFormat.XLSX);
        schema.setWordsCountFrom(10);
        schema.setWordsCountTo(100);
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTranslationCostsByTaskReportTest() {
        TranslationCostsPostEndingGenerateReportRequest request = new TranslationCostsPostEndingGenerateReportRequest();
        TranslationCostsPostEndingGenerateReportRequest.ByTaskSchema schema = new TranslationCostsPostEndingGenerateReportRequest.ByTaskSchema();
        schema.setUnit(Unit.WORDS);
        schema.setCurrency(Currency.PLN);
        schema.setFormat(ReportsFormat.XLSX);
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setFullTranslation(fullTranslationRate);
        baseRates.setProofread(proofreadRate);
        schema.setBaseRates(baseRates);
        NetRateSchemes netRateSchemes = new NetRateSchemes();
        Match match = new Match();
        match.setMatchType(MatchType.PERFECT);
        match.setPrice(fullTranslationRate);
        netRateSchemes.setTmMatch(singletonList(match));
        schema.setNetRateSchemes(netRateSchemes);
        schema.setUseCategoryBasedProofreadRates(true);
        schema.setUseTmEditDistance(true);
        schema.setSkipArchiving(false);
        schema.setTaskIds(Arrays.asList(7L, 8L));
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateCostsEstimationByTaskReportTest() {
        CostEstimationPostEditingGenerateReportRequest request = new CostEstimationPostEditingGenerateReportRequest();
        CostEstimationPostEditingGenerateReportRequest.ByTaskSchema schema = new CostEstimationPostEditingGenerateReportRequest.ByTaskSchema();
        schema.setCurrency(Currency.DDK);
        schema.setSkipArchiving(true);
        schema.setTaskIds(singletonList(7L));
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTranslatorAccuracyReportTest() {
        TranslateAccuracyGenerateReportRequest request = new TranslateAccuracyGenerateReportRequest();
        TranslateAccuracyGenerateReportRequest.Schema schema = new TranslateAccuracyGenerateReportRequest.Schema();
        schema.setUnit(Unit.WORDS);
        schema.setFormat(ReportsFormat.XLSX);
        schema.setMatchScoreCategories(Arrays.asList("100-90", "89-80"));
        schema.setLanguageId(languageId);
        schema.setUserIds(singletonList(userId));
        schema.setFileIds(singletonList(fileId));
        schema.setDirectoryIds(singletonList(directoryId));
        schema.setBranchIds(singletonList(branchId));
        schema.setLabelIds(singletonList(labelId));
        schema.setLabelIncludeType(LabelIncludeType.STRINGS_WITHOUT_LABEL);
        schema.setSkipArchiving(true);
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generatePreTranslateAccuracyNewFieldsReportTest() {
        PreTranslateAccuracyGenerateReportRequest request = new PreTranslateAccuracyGenerateReportRequest();
        request.setName("pre-translate-accuracy");
        PreTranslateAccuracyGenerateReportRequest.GeneralSchema schema = new PreTranslateAccuracyGenerateReportRequest.GeneralSchema();
        schema.setMatchScoreCategories(singletonList("100-90"));
        schema.setSkipArchiving(true);
        schema.setLanguageId(languageId);
        schema.setFileIds(singletonList(fileId));
        schema.setDirectoryIds(singletonList(directoryId));
        schema.setBranchIds(singletonList(branchId));
        schema.setLabelIds(singletonList(labelId));
        schema.setLabelIncludeType(LabelIncludeType.STRINGS_WITH_LABEL);
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateSavingActivityReportTest() {
        SavingActivityGenerateReportRequest request = new SavingActivityGenerateReportRequest();
        SavingActivityGenerateReportRequest.Schema schema = new SavingActivityGenerateReportRequest.Schema();
        schema.setUnit(Unit.WORDS);
        schema.setFormat(ReportsFormat.CSV);
        schema.setMode("currency");
        schema.setFileIds(singletonList(fileId));
        schema.setDirectoryIds(singletonList(directoryId));
        schema.setBranchIds(singletonList(branchId));
        schema.setUserIds(singletonList(userId));
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTopMembersReportTest() {
        TopMembersGenerateReportRequest request = new TopMembersGenerateReportRequest();
        TopMembersGenerateReportRequest.Schema schema = new TopMembersGenerateReportRequest.Schema();
        schema.setUnit(Unit.WORDS);
        schema.setFormat(ReportsFormat.XLSX);
        schema.setUserIds(Arrays.asList(1L, 2L));
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void generateTranslationActivityReportTest() {
        TranslationActivityGenerateReportRequest request = new TranslationActivityGenerateReportRequest();
        TranslationActivityGenerateReportRequest.Schema schema = new TranslationActivityGenerateReportRequest.Schema();
        schema.setUnit(Unit.STRINGS);
        schema.setUserIds(singletonList(3L));
        request.setSchema(schema);

        ResponseObject<ReportStatus> response = this.getReportsApi().generateReport(projectId, request);
        assertEquals(id, response.getData().getIdentifier());
    }

    @Test
    public void addHourlyReportSettingsTemplateTest() {
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setHourly(20F);
        IndividualRate individualRate = new IndividualRate();
        individualRate.setLanguageIds(singletonList(engLanguageId));
        individualRate.setUserIds(singletonList(20L));
        individualRate.setHourly(25F);
        ReportSettingsTemplate.Config config = new ReportSettingsTemplate.Config();
        config.setBaseRates(baseRates);
        config.setIndividualRates(singletonList(individualRate));

        ReportSettingsTemplate request = new ReportSettingsTemplate();
        request.setName("hourly template");
        request.setCurrency(Currency.PLN);
        request.setUnit(Unit.HOURS);
        request.setConfig(config);
        request.setIsPublic(true);

        ReportSettingsTemplate response = this.getReportsApi().addReportSettingsTemplate(projectId, request).getData();
        assertEquals(3L, response.getId());
        assertEquals(Unit.HOURS, response.getUnit());
        assertEquals(Currency.PLN, response.getCurrency());
        assertEquals(config, response.getConfig());
        assertEquals(20F, response.getConfig().getBaseRates().getHourly());
        assertEquals(25F, response.getConfig().getIndividualRates().get(0).getHourly());
        assertTrue(response.getIsPublic());
        assertFalse(response.getIsGlobal());
    }

    @Test
    public void addPostEditingReportSettingsTemplateTest() {
        BaseRatesForm baseRates = new BaseRatesForm();
        baseRates.setFullTranslation(fullTranslationRate);
        baseRates.setProofread(proofreadRate);
        IndividualRate individualRate = new IndividualRate();
        individualRate.setLanguageIds(singletonList(engLanguageId));
        individualRate.setUserIds(singletonList(20L));
        individualRate.setFullTranslation(fullTranslationRate);
        individualRate.setProofread(proofreadRate);
        Match perfect = new Match();
        perfect.setMatchType(MatchType.PERFECT);
        perfect.setPrice(fullTranslationRate);
        Match full = new Match();
        full.setMatchType(MatchType.OPTION_100);
        full.setPrice(fullTranslationRate);
        NetRateSchemes netRateSchemes = new NetRateSchemes();
        netRateSchemes.setTmMatch(singletonList(perfect));
        netRateSchemes.setMtMatch(singletonList(full));
        netRateSchemes.setAiMatch(singletonList(full));
        netRateSchemes.setSuggestionMatch(singletonList(full));
        ReportSettingsTemplate.Config config = new ReportSettingsTemplate.Config();
        config.setBaseRates(baseRates);
        config.setIndividualRates(singletonList(individualRate));
        config.setNetRateSchemes(netRateSchemes);
        config.setCalculateInternalMatches(true);
        config.setIncludePreTranslatedStrings(false);
        config.setUseCategoryBasedProofreadRates(true);
        config.setUseTmEditDistance(false);

        ReportSettingsTemplate request = new ReportSettingsTemplate();
        request.setName("post-editing template");
        request.setCurrency(Currency.DDK);
        request.setUnit(Unit.WORDS);
        request.setConfig(config);

        ReportSettingsTemplate response = this.getReportsApi().addReportSettingsTemplate(projectId, request).getData();
        assertEquals(4L, response.getId());
        assertEquals(Currency.DDK, response.getCurrency());
        ReportSettingsTemplate.Config responseConfig = response.getConfig();
        assertEquals(baseRates, responseConfig.getBaseRates());
        assertEquals(netRateSchemes, responseConfig.getNetRateSchemes());
        assertEquals(singletonList(individualRate), responseConfig.getIndividualRates());
        assertTrue(responseConfig.getCalculateInternalMatches());
        assertFalse(responseConfig.getIncludePreTranslatedStrings());
        assertTrue(responseConfig.getUseCategoryBasedProofreadRates());
        assertFalse(responseConfig.getUseTmEditDistance());
    }

    @Test
    public void legacyGroupTaskUsageTaskTypeIsSerializedAsTypeTasks() {
        GroupTaskUsageGenerateReportRequest.Schema schema = new GroupTaskUsageGenerateReportRequest.Schema();
        schema.setTaskType(2);
        assertEquals(2, schema.getTypeTasks());
        assertEquals(2, schema.getTaskType());

        String json = new JacksonJsonTransformer().convert(schema);
        assertEquals("{\"typeTasks\":2}", json);
    }
}
