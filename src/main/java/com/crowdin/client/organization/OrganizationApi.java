package com.crowdin.client.organization;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.organization.model.CustomSpellchecker;
import com.crowdin.client.organization.model.CustomSpellcheckerResponseList;
import com.crowdin.client.organization.model.CustomSpellcheckerResponseObject;
import com.crowdin.client.organization.model.ExternalQaCheck;
import com.crowdin.client.organization.model.ExternalQaCheckResponseList;
import com.crowdin.client.organization.model.Organization;
import com.crowdin.client.organization.model.OrganizationAuthSettings;
import com.crowdin.client.organization.model.OrganizationAuthSettingsResponseObject;
import com.crowdin.client.organization.model.OrganizationResponseObject;

import java.util.Map;
import java.util.Optional;

public class OrganizationApi extends CrowdinApi {
    public OrganizationApi(Credentials credentials) {
        super(credentials);
    }

    public OrganizationApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @return organization info
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.organization.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Organization> getOrganization() throws HttpException, HttpBadRequestException {
        OrganizationResponseObject organizationResponseObject = this.httpClient.get(this.url + "/organization", new HttpRequestConfig(), OrganizationResponseObject.class);
        return ResponseObject.of(organizationResponseObject.getData());
    }

    /**
     * @return organization authentication settings
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.organization.auth-settings.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<OrganizationAuthSettings> getOrganizationAuthSettings() throws HttpException, HttpBadRequestException {
        OrganizationAuthSettingsResponseObject organizationAuthSettingsResponseObject = this.httpClient.get(this.url + "/organization/auth-settings", new HttpRequestConfig(), OrganizationAuthSettingsResponseObject.class);
        return ResponseObject.of(organizationAuthSettingsResponseObject.getData());
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of custom spellcheckers
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-spellcheckers.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<CustomSpellchecker> listCustomSpellcheckers(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        CustomSpellcheckerResponseList customSpellcheckerResponseList = this.httpClient.get(this.url + "/custom-spellcheckers", new HttpRequestConfig(queryParams), CustomSpellcheckerResponseList.class);
        return CustomSpellcheckerResponseList.to(customSpellcheckerResponseList);
    }

    /**
     * @param customSpellcheckerId custom spellchecker identifier
     * @return custom spellchecker
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-spellcheckers.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<CustomSpellchecker> getCustomSpellchecker(Long customSpellcheckerId) throws HttpException, HttpBadRequestException {
        CustomSpellcheckerResponseObject customSpellcheckerResponseObject = this.httpClient.get(this.url + "/custom-spellcheckers/" + customSpellcheckerId, new HttpRequestConfig(), CustomSpellcheckerResponseObject.class);
        return ResponseObject.of(customSpellcheckerResponseObject.getData());
    }

    /**
     * @param projectId project identifier (optional filter)
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of external QA checks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.external-qa-checks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ExternalQaCheck> listExternalQaChecks(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ExternalQaCheckResponseList externalQaCheckResponseList = this.httpClient.get(this.url + "/external-qa-checks", new HttpRequestConfig(queryParams), ExternalQaCheckResponseList.class);
        return ExternalQaCheckResponseList.to(externalQaCheckResponseList);
    }
}
