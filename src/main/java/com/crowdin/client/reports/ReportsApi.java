package com.crowdin.client.reports;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.reports.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReportsApi extends CrowdinApi {
    public ReportsApi(Credentials credentials) {
        super(credentials);
    }

    public ReportsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return report status
     */
    public ResponseObject<ReportStatus> generateReport(Long projectId, GenerateReportRequest request) throws HttpException, HttpBadRequestException {
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/reports", request, new HttpRequestConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return report status
     */
    public ResponseObject<ReportStatus> checkReportGenerationStatus(Long projectId, String reportId) throws HttpException, HttpBadRequestException {
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/" + reportId, new HttpRequestConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadReport(Long projectId, String reportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/" + reportId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of report settings template
     */
    public ResponseList<ReportSettingsTemplate> listReportSettingsTemplate(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ReportSettingsTemplateList reportSettingsTemplateList = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/settings-templates", new HttpRequestConfig(queryParams), ReportSettingsTemplateList.class);
        return ReportSettingsTemplateList.to(reportSettingsTemplateList);
    }

    /**
     * @param projectId project identifier
     * @param request   request
     * @return report settings template
     */
    public ResponseObject<ReportSettingsTemplate> addReportSettingsTemplate(Long projectId, ReportSettingsTemplate request) throws HttpException, HttpBadRequestException {
        ReportSettingsTemplateResponseObject responseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/reports/settings-templates", request, new HttpRequestConfig(), ReportSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId                project identifier
     * @param reportSettingsTemplateId report settings template identifier
     * @return report settings template
     */
    public ResponseObject<ReportSettingsTemplate> getReportSettingsTemplate(Long projectId, Long reportSettingsTemplateId) throws HttpException, HttpBadRequestException {
        ReportSettingsTemplateResponseObject responseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, new HttpRequestConfig(), ReportSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId                project identifier
     * @param reportSettingsTemplateId report settings template identifier
     * @param request                  request object
     * @return report settings template
     */
    public ResponseObject<ReportSettingsTemplate> editReportSettingsTemplate(Long projectId, Long reportSettingsTemplateId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        ReportSettingsTemplateResponseObject responseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, request, new HttpRequestConfig(), ReportSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId                project identifier
     * @param reportSettingsTemplateId report settings template identifier
     */
    public void deleteReportSettingsTemplate(Long projectId, Long reportSettingsTemplateId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/settings-templates/" + reportSettingsTemplateId, new HttpRequestConfig(), Void.class);
    }
}
