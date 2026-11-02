package com.crowdin.client.applications;

import com.crowdin.client.applications.consents.model.AddApplicationConsentRequest;
import com.crowdin.client.applications.consents.model.ApplicationConsent;
import com.crowdin.client.applications.consents.model.ApplicationConsentResponseList;
import com.crowdin.client.applications.consents.model.ApplicationConsentResponseObject;
import com.crowdin.client.applications.consents.model.ListApplicationConsentsParams;
import com.crowdin.client.applications.installations.model.ApplicationInstallation;
import com.crowdin.client.applications.installations.model.ApplicationInstallationResponseList;
import com.crowdin.client.applications.installations.model.ApplicationInstallationResponseObject;
import com.crowdin.client.applications.installations.model.ApplicationInstallationUpdate;
import com.crowdin.client.applications.installations.model.ApplicationInstallationUpdateResponseObject;
import com.crowdin.client.applications.installations.model.ApplyApplicationInstallationUpdateRequest;
import com.crowdin.client.applications.installations.model.InstallApplicationRequest;
import com.crowdin.client.applications.installations.model.ListApplicationInstallationsParams;
import com.crowdin.client.applications.installations.model.UploadApplicationBundleRequest;
import com.crowdin.client.applications.integrations.model.IntegrationFile;
import com.crowdin.client.applications.integrations.model.IntegrationFileProgress;
import com.crowdin.client.applications.integrations.model.IntegrationFileProgressResponseObject;
import com.crowdin.client.applications.integrations.model.IntegrationFileResponseList;
import com.crowdin.client.applications.integrations.model.IntegrationJob;
import com.crowdin.client.applications.integrations.model.IntegrationJobId;
import com.crowdin.client.applications.integrations.model.IntegrationJobIdResponseObject;
import com.crowdin.client.applications.integrations.model.IntegrationJobResponseList;
import com.crowdin.client.applications.integrations.model.IntegrationLoginField;
import com.crowdin.client.applications.integrations.model.IntegrationLoginFieldResponseList;
import com.crowdin.client.applications.integrations.model.IntegrationLoginRequest;
import com.crowdin.client.applications.integrations.model.IntegrationSyncProvider;
import com.crowdin.client.applications.integrations.model.IntegrationSyncSettingsResponseObject;
import com.crowdin.client.applications.integrations.model.UpdateIntegrationCrowdinFilesRequest;
import com.crowdin.client.applications.integrations.model.UpdateIntegrationFilesRequest;
import com.crowdin.client.applications.integrations.model.UpdateIntegrationSettingsRequest;
import com.crowdin.client.applications.integrations.model.UpdateIntegrationSyncSettingsRequest;
import com.crowdin.client.applications.model.*;
import com.crowdin.client.applications.storage.model.AddApplicationKvRecordRequest;
import com.crowdin.client.applications.storage.model.ApplicationKvRecord;
import com.crowdin.client.applications.storage.model.ApplicationKvRecordResponseList;
import com.crowdin.client.applications.storage.model.ApplicationKvRecordResponseObject;
import com.crowdin.client.applications.storage.model.ListApplicationKvRecordsParams;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ApplicationsApi extends CrowdinApi {
    public ApplicationsApi(Credentials credentials) {
        super(credentials);
    }

    public ApplicationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> getApplicationData(String applicationIdentifier, String path) throws HttpException, HttpBadRequestException {
        return this.getApplicationData(applicationIdentifier, path, null);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param queryParams application-specific query parameters (entries with {@code null} values are skipped, values are sent as is)
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> getApplicationData(String applicationIdentifier, String path, Map<String, Object> queryParams) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(toUrlParams(queryParams)), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> updateOrRestoreApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        return this.updateOrRestoreApplicationData(applicationIdentifier, path, request, null);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @param queryParams application-specific query parameters (entries with {@code null} values are skipped, values are sent as is)
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> updateOrRestoreApplicationData(String applicationIdentifier, String path, Map<String, Object> request, Map<String, Object> queryParams) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.put(builtUrl, request, new HttpRequestConfig(toUrlParams(queryParams)), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> addApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        return this.addApplicationData(applicationIdentifier, path, request, null);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @param queryParams application-specific query parameters (entries with {@code null} values are skipped, values are sent as is)
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> addApplicationData(String applicationIdentifier, String path, Map<String, Object> request, Map<String, Object> queryParams) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(toUrlParams(queryParams)), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationData(String applicationIdentifier, String path) throws HttpException, HttpBadRequestException {
        this.deleteApplicationData(applicationIdentifier, path, null);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param queryParams application-specific query parameters (entries with {@code null} values are skipped, values are sent as is)
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationData(String applicationIdentifier, String path, Map<String, Object> queryParams) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(toUrlParams(queryParams)), Void.class);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> editApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        return this.editApplicationData(applicationIdentifier, path, request, null);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @param queryParams application-specific query parameters (entries with {@code null} values are skipped, values are sent as is)
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> editApplicationData(String applicationIdentifier, String path, Map<String, Object> request, Map<String, Object> queryParams) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(toUrlParams(queryParams)), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param identifier identifier of the application installation
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> getApplicationInstallation(String identifier) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param limit A maximum number of items to retrieve
     * @param offset A starting offset in the collection
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ApplicationInstallation> listApplicationInstallations(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListApplicationInstallationsParams params = new ListApplicationInstallationsParams();
        params.setLimit(limit);
        params.setOffset(offset);
        return this.listApplicationInstallations(params);
    }

    /**
     * @param params filter, sorting and pagination params
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ApplicationInstallation> listApplicationInstallations(ListApplicationInstallationsParams params) throws HttpException, HttpBadRequestException {
        ListApplicationInstallationsParams query = Optional.ofNullable(params).orElse(new ListApplicationInstallationsParams());
        String builtUrl = String.format("%s/applications/installations", this.url);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "installedBy", Optional.ofNullable(query.getInstalledBy()),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(query.getOrderBy())),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset())
        );
        ApplicationInstallationResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ApplicationInstallationResponseList.class);
        return ApplicationInstallationResponseList.to(response);
    }

    /**
     * @param request request object
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> installApplication(InstallApplicationRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations", this.url);
        ApplicationInstallationResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param identifier identifier of the application installation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationInstallation(String identifier) throws HttpException, HttpBadRequestException {
        this.deleteApplicationInstallation(identifier, null);
    }

    /**
     * @param identifier identifier of the application installation
     * @param force force delete the application installation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationInstallation(String identifier, Boolean force) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "force", Optional.ofNullable(force)
        );
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param identifier identifier of the application installation
     * @param request request object
     * @return updated application installation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> editApplicationInstallation(String identifier, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Upload a bundle archive for a serverless app installed from manifest content
     *
     * @param identifier identifier of the application installation
     * @param request request object
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.bundles.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.bundles.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> uploadApplicationBundle(String identifier, UploadApplicationBundleRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s/bundles", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Returns the diff between the currently installed application and the latest cached manifest
     *
     * @param identifier identifier of the application installation
     * @return pending application installation update
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.update.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.update.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallationUpdate> getApplicationInstallationUpdate(String identifier) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s/update", this.url, identifier);
        ApplicationInstallationUpdateResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ApplicationInstallationUpdateResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Apply the latest cached manifest to an installed application
     *
     * @param identifier identifier of the application installation
     * @param request request object
     * @return updated application installation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.update.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.update.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> applyApplicationInstallationUpdate(String identifier, ApplyApplicationInstallationUpdateRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s/update", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param params filter, sorting and pagination params
     * @return list of application consents
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.consents.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ApplicationConsent> listApplicationConsents(ListApplicationConsentsParams params) throws HttpException, HttpBadRequestException {
        ListApplicationConsentsParams query = Optional.ofNullable(params).orElse(new ListApplicationConsentsParams());
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "identifier", Optional.ofNullable(query.getIdentifier()),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(query.getOrderBy())),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset())
        );
        String builtUrl = String.format("%s/applications/consents", this.url);
        ApplicationConsentResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ApplicationConsentResponseList.class);
        return ApplicationConsentResponseList.to(response);
    }

    /**
     * @param request request object
     * @return newly created application consent
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.consents.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationConsent> addApplicationConsent(AddApplicationConsentRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/consents", this.url);
        ApplicationConsentResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationConsentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param consentId consent decision identifier
     * @param request   request object
     * @return updated application consent
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.consents.patch" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationConsent> editApplicationConsent(Long consentId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/consents/%s", this.url, consentId);
        ApplicationConsentResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationConsentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param consentId consent decision identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.consents.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationConsent(Long consentId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/consents/%s", this.url, consentId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Application KV storage
    // ---------------------------------------------------------------------------------------------------------------

    /**
     * Requires the application's own access token - personal access tokens are not supported
     *
     * @param applicationIdentifier identifier of the application
     * @param params filter, sorting and pagination params
     * @return list of application KV records
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.storage.kv.records.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.storage.kv.records.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ApplicationKvRecord> listApplicationKvRecords(String applicationIdentifier, ListApplicationKvRecordsParams params) throws HttpException, HttpBadRequestException {
        ListApplicationKvRecordsParams query = Optional.ofNullable(params).orElse(new ListApplicationKvRecordsParams());
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "prefix", Optional.ofNullable(query.getPrefix()),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(query.getOrderBy())),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset())
        );
        String builtUrl = String.format("%s/applications/%s/storage/kv/records", this.url, applicationIdentifier);
        ApplicationKvRecordResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ApplicationKvRecordResponseList.class);
        return ApplicationKvRecordResponseList.to(response);
    }

    /**
     * Requires the application's own access token - personal access tokens are not supported.
     * <p>
     * Note: the client omits {@code null}-valued entries inside maps when serializing the request, e.g. {@code {"a": null, "b": 1}} is sent as {@code {"b": 1}}; {@code null} elements inside lists are kept.
     *
     * @param applicationIdentifier identifier of the application
     * @param request request object
     * @return newly created application KV record
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.storage.kv.records.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.storage.kv.records.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationKvRecord> addApplicationKvRecord(String applicationIdentifier, AddApplicationKvRecordRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/storage/kv/records", this.url, applicationIdentifier);
        ApplicationKvRecordResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationKvRecordResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Requires the application's own access token - personal access tokens are not supported
     *
     * @param applicationIdentifier identifier of the application
     * @param key key of the record (reserved characters are URL-encoded by the client)
     * @return application KV record
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.storage.kv.records.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.storage.kv.records.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationKvRecord> getApplicationKvRecord(String applicationIdentifier, String key) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/storage/kv/records/%s", this.url, applicationIdentifier, encodePathSegment(key));
        ApplicationKvRecordResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ApplicationKvRecordResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Requires the application's own access token - personal access tokens are not supported.
     * Supported paths: {@code /value}, {@code /ttl}. To remove the TTL send {@code null} for {@code /ttl} using
     * {@link com.crowdin.client.applications.storage.model.ApplicationKvRecordPatchRequest}
     * (a plain {@link PatchRequest} omits {@code null} values).
     * <p>
     * Use {@code ApplicationKvRecordPatchRequest} for records that may be {@code secret}: its {@code toString()}
     * masks every value except the {@code /ttl} one, while a plain {@link PatchRequest} prints the value as is.
     * <p>
     * Note: the client omits {@code null}-valued entries inside maps when serializing the request, e.g. {@code {"a": null, "b": 1}} is sent as {@code {"b": 1}}; {@code null} elements inside lists are kept.
     *
     * @param applicationIdentifier identifier of the application
     * @param key key of the record (reserved characters are URL-encoded by the client)
     * @param request request object
     * @return updated application KV record
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.storage.kv.records.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.storage.kv.records.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationKvRecord> editApplicationKvRecord(String applicationIdentifier, String key, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/storage/kv/records/%s", this.url, applicationIdentifier, encodePathSegment(key));
        ApplicationKvRecordResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationKvRecordResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * Requires the application's own access token - personal access tokens are not supported
     *
     * @param applicationIdentifier identifier of the application
     * @param key key of the record (reserved characters are URL-encoded by the client)
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.storage.kv.records.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.storage.kv.records.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationKvRecord(String applicationIdentifier, String key) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/storage/kv/records/%s", this.url, applicationIdentifier, encodePathSegment(key));
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // File-based integration applications (/applications/{applicationIdentifier}/api/...)
    // ---------------------------------------------------------------------------------------------------------------

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @return list of Crowdin files and folders
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.crowdin.files" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.crowdin.files" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<IntegrationFile> listIntegrationCrowdinFiles(String applicationIdentifier, Long projectId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId)
        );
        String builtUrl = String.format("%s/applications/%s/api/crowdin-files", this.url, applicationIdentifier);
        IntegrationFileResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationFileResponseList.class);
        return IntegrationFileResponseList.to(response);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @return list of integration files and folders
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.integration.files" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.integration.files" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<IntegrationFile> listIntegrationFiles(String applicationIdentifier, Long projectId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId)
        );
        String builtUrl = String.format("%s/applications/%s/api/integration-files", this.url, applicationIdentifier);
        IntegrationFileResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationFileResponseList.class);
        return IntegrationFileResponseList.to(response);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @param fileId Crowdin file identifier
     * @return file translation progress
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.file.progress" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.file.progress" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<IntegrationFileProgress> getIntegrationFileProgress(String applicationIdentifier, Long projectId, Long fileId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "fileId", Optional.ofNullable(fileId)
        );
        String builtUrl = String.format("%s/applications/%s/api/file-progress", this.url, applicationIdentifier);
        IntegrationFileProgressResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationFileProgressResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param request request object
     * @return identifier of the started job
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.crowdin.update" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.crowdin.update" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<IntegrationJobId> updateIntegrationCrowdinFiles(String applicationIdentifier, UpdateIntegrationCrowdinFilesRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/crowdin-update", this.url, applicationIdentifier);
        IntegrationJobIdResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), IntegrationJobIdResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param request request object
     * @return identifier of the started job
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.integration.update" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.integration.update" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<IntegrationJobId> updateIntegrationFiles(String applicationIdentifier, UpdateIntegrationFilesRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/integration-update", this.url, applicationIdentifier);
        IntegrationJobIdResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), IntegrationJobIdResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @param limit number of jobs to return
     * @param offset number of jobs to skip
     * @return list of jobs
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.job.list" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.job.list" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<IntegrationJob> listIntegrationJobs(String applicationIdentifier, Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        String builtUrl = String.format("%s/applications/%s/api/all-jobs", this.url, applicationIdentifier);
        IntegrationJobResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationJobResponseList.class);
        return IntegrationJobResponseList.to(response);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @param jobId job identifier
     * @return job info
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.job.info" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.job.info" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<IntegrationJob> getIntegrationJobInfo(String applicationIdentifier, Long projectId, String jobId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "jobId", Optional.ofNullable(jobId)
        );
        String builtUrl = String.format("%s/applications/%s/api/job-info", this.url, applicationIdentifier);
        IntegrationJobResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationJobResponseList.class);
        return IntegrationJobResponseList.to(response);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @param jobId job identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.job.cancel" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.job.cancel" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void cancelIntegrationJob(String applicationIdentifier, Long projectId, String jobId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "jobId", Optional.ofNullable(jobId)
        );
        String builtUrl = String.format("%s/applications/%s/api/jobs", this.url, applicationIdentifier);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @return application settings
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.settings.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.settings.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> getIntegrationSettings(String applicationIdentifier, Long projectId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId)
        );
        String builtUrl = String.format("%s/applications/%s/api/settings", this.url, applicationIdentifier);
        ApplicationDataResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.settings.update" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.settings.update" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void updateIntegrationSettings(String applicationIdentifier, UpdateIntegrationSettingsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/settings", this.url, applicationIdentifier);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param projectId project identifier
     * @param provider sync settings provider
     * @return sync settings: either a {@code Map} of Crowdin file id to a list of language ids, or a {@code List} of file nodes
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.sync.settings.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.sync.settings.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Object> getIntegrationSyncSettings(String applicationIdentifier, Long projectId, IntegrationSyncProvider provider) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "provider", Optional.ofNullable(provider).map(p -> p.to(p))
        );
        String builtUrl = String.format("%s/applications/%s/api/sync-settings", this.url, applicationIdentifier);
        IntegrationSyncSettingsResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), IntegrationSyncSettingsResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.sync.settings.update" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.sync.settings.update" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void updateIntegrationSyncSettings(String applicationIdentifier, UpdateIntegrationSyncSettingsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/sync-settings", this.url, applicationIdentifier);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @return list of integration login form fields
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.integration.fields" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.integration.fields" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<IntegrationLoginField> listIntegrationLoginFields(String applicationIdentifier) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/login-fields", this.url, applicationIdentifier);
        IntegrationLoginFieldResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(), IntegrationLoginFieldResponseList.class);
        return IntegrationLoginFieldResponseList.to(response);
    }

    /**
     * @param applicationIdentifier identifier of the integration application
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.integrations.integration.login" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.integrations.integration.login" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void loginToIntegration(String applicationIdentifier, IntegrationLoginRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/login", this.url, applicationIdentifier);
        this.httpClient.post(builtUrl, request, new HttpRequestConfig(), Void.class);
    }

    private static Map<String, Optional<Object>> toUrlParams(Map<String, Object> queryParams) {
        Map<String, Optional<Object>> urlParams = new LinkedHashMap<>();
        if (queryParams != null) {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                urlParams.put(entry.getKey(), Optional.ofNullable(entry.getValue()));
            }
        }
        return urlParams;
    }

    private static String encodePathSegment(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
