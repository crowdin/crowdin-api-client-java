package com.crowdin.client.reports;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.reports.model.GenerateReportRequest;
import com.crowdin.client.reports.model.ReportStatus;
import com.crowdin.client.reports.model.ReportStatusResponseObject;

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
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/reports", request, new HttpConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return report status
     */
    public ResponseObject<ReportStatus> checkReportGenerationStatus(Long projectId, String reportId) throws HttpException, HttpBadRequestException {
        ReportStatusResponseObject reportStatusResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/" + reportId, new HttpConfig(), ReportStatusResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param reportId  report identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadReport(Long projectId, String reportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/reports/" + reportId + "/download", new HttpConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }
}
