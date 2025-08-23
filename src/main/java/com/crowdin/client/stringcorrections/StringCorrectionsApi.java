package com.crowdin.client.stringcorrections;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.stringcorrections.model.*;

import java.util.Map;
import java.util.Optional;

public class StringCorrectionsApi extends CrowdinApi {


    public StringCorrectionsApi(Credentials credentials) {
        super(credentials);
    }

    public StringCorrectionsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param params    query params
     * @return list of corrections
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Correction> listCorrections(Long projectId, ListCorrectionsQueryParams params) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.of(params.getStringId()),
                "orderBy", Optional.ofNullable(params.getOrderBy()),
                "denormalizePlaceholders", Optional.ofNullable(params.getDenormalizePlaceholders()),
                "limit", Optional.ofNullable(params.getLimit()),
                "offset", Optional.ofNullable(params.getOffset())
        );
        CorrectionResponseList response = this.httpClient.get(this.url + "/projects/" + projectId + "/corrections", new HttpRequestConfig(queryParams), CorrectionResponseList.class);
        return CorrectionResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created correction
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Correction> addCorrection(Long projectId, AddCorrectionRequest request) throws HttpException, HttpBadRequestException {
        CorrectionResponseObject response = this.httpClient.post(this.url + "/projects/" + projectId + "/corrections", request, new HttpRequestConfig(), CorrectionResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId    project identifier
     * @param correctionId correction identifier
     * @return correction object
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Correction> getCorrection(Long projectId, Long correctionId) throws HttpException, HttpBadRequestException {
        CorrectionResponseObject response = this.httpClient.get(this.url + "/projects/" + projectId + "/corrections/" + correctionId, new HttpRequestConfig(), CorrectionResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId  string identifier
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteCorrections(Long projectId, Long stringId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.of(stringId)
        );
        this.httpClient.delete(this.url + "/projects/" + projectId + "/corrections", new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param projectId    project identifier
     * @param correctionId correction identifier
     * @return newly created correction
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Correction> restoreCorrection(Long projectId, Long correctionId) throws HttpException, HttpBadRequestException {
        CorrectionResponseObject response = this.httpClient.put(this.url + "/projects/" + projectId + "/corrections/" + correctionId, null, new HttpRequestConfig(), CorrectionResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId    project identifier
     * @param correctionId correction identifier
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/String-Corrections/operation/api.projects.corrections.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteCorrection(Long projectId, Long correctionId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/corrections/" + correctionId, new HttpRequestConfig(), Void.class);
    }

}
