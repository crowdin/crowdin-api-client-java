package com.crowdin.client.distributions;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.distributions.model.AddDistributionRequest;
import com.crowdin.client.distributions.model.Distribution;
import com.crowdin.client.distributions.model.DistributionRelease;
import com.crowdin.client.distributions.model.DistributionReleaseResponseObject;
import com.crowdin.client.distributions.model.DistributionResponseList;
import com.crowdin.client.distributions.model.DistributionResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DistributionsApi extends CrowdinApi {

    public DistributionsApi(Credentials credentials) {
        super(credentials);
    }

    public DistributionsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of distributions
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Distribution> listDistributions(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        DistributionResponseList distributionResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/distributions", new HttpRequestConfig(queryParams), DistributionResponseList.class);
        return DistributionResponseList.to(distributionResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created distribution
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Distribution> addDistribution(Long projectId, AddDistributionRequest request) throws HttpException, HttpBadRequestException {
        DistributionResponseObject distributionResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/distributions", request, new HttpRequestConfig(), DistributionResponseObject.class);
        return ResponseObject.of(distributionResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param hash hash
     * @return distribution
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Distribution> getDistribution(Long projectId, String hash) throws HttpException, HttpBadRequestException {
        DistributionResponseObject distributionResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/distributions/" + hash, new HttpRequestConfig(), DistributionResponseObject.class);
        return ResponseObject.of(distributionResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param hash hash
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteDistribution(Long projectId, String hash) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/distributions/" + hash, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param hash hash
     * @param request request object
     * @return updated distribution
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Distribution> editDistribution(Long projectId, String hash, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        DistributionResponseObject distributionResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/distributions/" + hash, request, new HttpRequestConfig(), DistributionResponseObject.class);
        return ResponseObject.of(distributionResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param hash hash
     * @return distribution release
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.release.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.release.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DistributionRelease> getDistributionRelease(Long projectId, String hash) throws HttpException, HttpBadRequestException {
        DistributionReleaseResponseObject distributionReleaseResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/distributions/" + hash + "/release", new HttpRequestConfig(), DistributionReleaseResponseObject.class);
        return ResponseObject.of(distributionReleaseResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param hash hash
     * @return distribution release
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.distributions.release.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.distributions.release.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DistributionRelease> createDistributionRelease(Long projectId, String hash) throws HttpException, HttpBadRequestException {
        DistributionReleaseResponseObject distributionReleaseResponseObject = this.httpClient.post(this.url +
                "/projects/" + projectId + "/distributions/" + hash + "/release", new DistributionRelease(), new HttpRequestConfig(),
                DistributionReleaseResponseObject.class);
        return ResponseObject.of(distributionReleaseResponseObject.getData());
    }
}
