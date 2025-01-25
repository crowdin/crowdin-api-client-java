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
                RequestMock.build(this.url + "/groups/" + groupId + "/reports/" + reportId, HttpGet.METHOD_NAME, "api/reports/groupReportStatusResponse.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/reports/" + reportId + "/download", HttpGet.METHOD_NAME, "api/reports/downloadLink.json"),
                RequestMock.build(this.url + "/reports", HttpPost.METHOD_NAME, "api/reports/generateOrganizationReportRequest.json", "api/reports/groupReportStatusResponse.json"),
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
                    setGroupBy(GroupingParameter.LANGUAGE);
                    setUserIds(Collections.singletonList(13L));
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
