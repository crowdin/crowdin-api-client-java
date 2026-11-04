package com.crowdin.client.advisors;

import com.crowdin.client.advisors.model.AdvisorCheck;
import com.crowdin.client.advisors.model.AdvisorCheckResponseObject;
import com.crowdin.client.advisors.model.AdvisorInsight;
import com.crowdin.client.advisors.model.AdvisorInsightResponseList;
import com.crowdin.client.advisors.model.AdvisorInsightResponseObject;
import com.crowdin.client.advisors.model.ListAdvisorInsightsParams;
import com.crowdin.client.advisors.model.RunAdvisorCheckRequest;
import com.crowdin.client.advisors.model.UpsertAdvisorInsightRequest;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdvisorsApi extends CrowdinApi {
    public AdvisorsApi(Credentials credentials) {
        super(credentials);
    }

    public AdvisorsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return advisor check job descriptor
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.advisors.checks.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.advisors.checks.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AdvisorCheck> runAdvisorCheck(Long projectId, RunAdvisorCheckRequest request) throws HttpException, HttpBadRequestException {
        AdvisorCheckResponseObject advisorCheckResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/advisors/checks", request, new HttpRequestConfig(), AdvisorCheckResponseObject.class);
        return ResponseObject.of(advisorCheckResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param checkId advisor check identifier
     * @return advisor check job descriptor
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.advisors.checks.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.advisors.checks.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AdvisorCheck> getAdvisorCheck(Long projectId, String checkId) throws HttpException, HttpBadRequestException {
        AdvisorCheckResponseObject advisorCheckResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/advisors/checks/" + checkId, new HttpRequestConfig(), AdvisorCheckResponseObject.class);
        return ResponseObject.of(advisorCheckResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param params query params
     * @return list of advisor insights; the pagination is an {@link com.crowdin.client.advisors.model.AdvisorInsightPagination} which also carries the total count
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.advisors.insights.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.advisors.insights.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<AdvisorInsight> listAdvisorInsights(Long projectId, ListAdvisorInsightsParams params) throws HttpException, HttpBadRequestException {
        ListAdvisorInsightsParams query = Optional.ofNullable(params).orElse(new ListAdvisorInsightsParams());
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset()),
                "isDismissed", Optional.ofNullable(query.getIsDismissed()),
                "status", Optional.ofNullable(
                        query.getStatus() == null ? null : query.getStatus().stream()
                                .map(status -> status.to(status))
                                .collect(Collectors.joining(","))
                ),
                "outcome", Optional.ofNullable(
                        query.getOutcome() == null ? null : query.getOutcome().stream()
                                .map(outcome -> outcome.to(outcome))
                                .collect(Collectors.joining(","))
                )
        );
        AdvisorInsightResponseList advisorInsightResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/advisors/insights", new HttpRequestConfig(queryParams), AdvisorInsightResponseList.class);
        return AdvisorInsightResponseList.to(advisorInsightResponseList);
    }

    /**
     * @param projectId project identifier
     * @param insightId insight identifier
     * @param request request object (only the {@code /isDismissed} path is supported)
     * @return updated advisor insight
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.advisors.insights.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.advisors.insights.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AdvisorInsight> editAdvisorInsight(Long projectId, Long insightId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        AdvisorInsightResponseObject advisorInsightResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/advisors/insights/" + insightId, request, new HttpRequestConfig(), AdvisorInsightResponseObject.class);
        return ResponseObject.of(advisorInsightResponseObject.getData());
    }

    /**
     * Publishes the result of an application's {@code advisor-inspector} module check.
     * Subsequent calls for the same module overwrite the previous insight.
     *
     * @param projectId project identifier
     * @param applicationIdentifier application identifier
     * @param moduleKey application module key
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.applications.modules.advisors.insights.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.applications.modules.advisors.insights.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void upsertApplicationAdvisorInsight(Long projectId, String applicationIdentifier, String moduleKey, UpsertAdvisorInsightRequest request) throws HttpException, HttpBadRequestException {
        this.httpClient.put(this.url + "/projects/" + projectId + "/applications/" + applicationIdentifier + "/modules/" + moduleKey + "/advisors/insights", request, new HttpRequestConfig(), Void.class);
    }
}
