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
     * @param groupId group identifier
     * @param request request object
     * @return group report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.reports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> generateGroupReport(Long groupId, GenerateGroupReportRequest request) throws HttpException, HttpBadRequestException {
        String url = this.url + "/groups/" + groupId + "/reports";
        GroupReportStatusResponseObject responseObject = this.httpClient.post(url, request, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param groupId  group identifier
     * @param reportId report identifier
     * @return group report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.reports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> checkGroupReportGenerationStatus(Long groupId, String reportId) throws HttpException, HttpBadRequestException {
        String url = this.url + "/groups/" + groupId + "/reports/" + reportId;
        GroupReportStatusResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param groupId  group identifier
     * @param reportId report identifier
     * @return report download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.reports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadGroupReport(Long groupId, String reportId) throws HttpException, HttpBadRequestException {
        String url = this.url + "/groups/" + groupId + "/reports/" + reportId + "/download";
        DownloadLinkResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param request request object
     * @return organization report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> generateOrganizationReport(GenerateGroupReportRequest request) throws HttpException, HttpBadRequestException {
        String url = this.url + "/reports";
        GroupReportStatusResponseObject responseObject = this.httpClient.post(url, request, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param reportId report identifier
     * @return organization report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> checkOrganizationReportGenerationStatus(String reportId) throws HttpException, HttpBadRequestException {
        String url = this.url + "/reports/" + reportId;
        GroupReportStatusResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param reportId report identifier
     * @return report download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadOrganizationReport(String reportId) throws HttpException, HttpBadRequestException {
        String url = this.url + "/reports/" + reportId + "/download";
        DownloadLinkResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ReportStatus> generateReport(Long projectId, GenerateReportRequest request) throws HttpException, HttpBadRequestException {
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/reports", request, new HttpRequestConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ReportStatus> checkReportGenerationStatus(Long projectId, String reportId) throws HttpException, HttpBadRequestException {
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/" + reportId, new HttpRequestConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.download.download" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
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
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.settings-templates.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.settings-templates.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
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
     * @param request   request object
     * @return report settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.settings-templates.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.settings-templates.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ReportSettingsTemplate> addReportSettingsTemplate(Long projectId, ReportSettingsTemplate request) throws HttpException, HttpBadRequestException {
        ReportSettingsTemplateResponseObject responseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/reports/settings-templates", request, new HttpRequestConfig(), ReportSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId                project identifier
     * @param reportSettingsTemplateId report settings template identifier
     * @return report settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.settings-templates.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.settings-templates.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
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
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.reports.settings-templates.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.reports.settings-templates.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ReportSettingsTemplate> editReportSettingsTemplate(Long projectId, Long reportSettingsTemplateId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        ReportSettingsTemplateResponseObject responseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/reports/settings-templates/" + reportSettingsTemplateId, request, new HttpRequestConfig(), ReportSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId                project identifier
     * @param reportSettingsTemplateId report settings template identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.settings-templates.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.settings-templates.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteReportSettingsTemplate(Long projectId, Long reportSettingsTemplateId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/settings-templates/" + reportSettingsTemplateId, new HttpRequestConfig(), Void.class);
    }

    // -- REPORT ARCHIVES -- //

    /**
     * @param userId    user identifier
     * @param scopeType Filter only project report archives (scopeType=project)
     * @param scopeId   Filter archives by spicific scope id (default 25)
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of report settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.reports.archives.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ReportArchive> listReportArchives(Long userId, String scopeType, Long scopeId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives/");
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "scopeType", Optional.ofNullable(scopeType),
                "scopeId", Optional.ofNullable(scopeId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ReportArchiveList responseObject = this.httpClient.get(url, new HttpRequestConfig(queryParams), ReportArchiveList.class);
        return ReportArchiveList.to(responseObject);
    }

    /**
     * @param userId    user identifier
     * @param archiveId archive identifier
     * @return download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.reports.archives.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ReportArchive> getReportArchive(Long userId, Long archiveId) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives/" + archiveId);
        ReportArchiveResponseObject reportArchiveResponseObject = this.httpClient.get(url, new HttpRequestConfig(), ReportArchiveResponseObject.class);
        return ResponseObject.of(reportArchiveResponseObject.getData());
    }

    /**
     * @param userId    user identifier
     * @param archiveId archive identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.reports.archives.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteReportArchive(Long userId, Long archiveId) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives/" + archiveId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId    user identifier
     * @param archiveId archive identifier
     * @param request   request object
     * @return report settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.reports.archives.exports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.exports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> exportReportArchive(Long userId, Long archiveId, ExportReportRequest request) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives/" + archiveId + "/exports");
        GroupReportStatusResponseObject responseObject = this.httpClient.post(url, request, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param archiveId archive identifier
     * @param exportId  export identifier
     * @return export report status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.exports.get" target="_blank"><b> API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.exports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupReportStatus> checkReportArchiveExportStatus(Long userId, Long archiveId, String exportId) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives/" + archiveId + "/exports/" + exportId);
        GroupReportStatusResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), GroupReportStatusResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param userId    user identifier
     * @param archiveId archive identifier
     * @param exportId  export identifier
     * @return report archive download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.reports.archives.exports.download.get" target="_blank"><b> API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.reports.archives.exports.download.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadReportArchive(Long userId, Long archiveId, String exportId) throws HttpException, HttpBadRequestException {
        String url = getReportArchivesPath(userId, "reports/archives" + archiveId + "/exports/" + exportId + "/download");
        DownloadLinkResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * Build an url with or without userId prefix
     *
     * @param userId user identifier (for crowdin.com only)
     * @param path   sub-path of the url
     * @return url with userId prefix if exist
     */
    private String getReportArchivesPath(Long userId, String path) {
        return userId != null ? String.format("%s/users/%d/%s", this.url, userId, path) : String.format("%s/%s", this.url, path);
    }
}
