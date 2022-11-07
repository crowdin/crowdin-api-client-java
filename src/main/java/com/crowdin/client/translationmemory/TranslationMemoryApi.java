package com.crowdin.client.translationmemory;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.translationmemory.model.AddTranslationMemoryRequest;
import com.crowdin.client.translationmemory.model.TranslationMemory;
import com.crowdin.client.translationmemory.model.TranslationMemoryExportRequest;
import com.crowdin.client.translationmemory.model.TranslationMemoryExportStatus;
import com.crowdin.client.translationmemory.model.TranslationMemoryExportStatusResponseObject;
import com.crowdin.client.translationmemory.model.TranslationMemoryImportRequest;
import com.crowdin.client.translationmemory.model.TranslationMemoryImportStatus;
import com.crowdin.client.translationmemory.model.TranslationMemoryImportStatusResponseObject;
import com.crowdin.client.translationmemory.model.TranslationMemoryResponseList;
import com.crowdin.client.translationmemory.model.TranslationMemoryResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TranslationMemoryApi extends CrowdinApi {
    public TranslationMemoryApi(Credentials credentials) {
        super(credentials);
    }

    public TranslationMemoryApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param groupId group identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of translation memories
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<TranslationMemory> listTms(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TranslationMemoryResponseList translationMemoryResponseList = this.httpClient.get(this.url + "/tms", new HttpRequestConfig(queryParams), TranslationMemoryResponseList.class);
        return TranslationMemoryResponseList.to(translationMemoryResponseList);
    }

    /**
     * @param request request object
     * @return newly created translation memory
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemory> addTm(AddTranslationMemoryRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.post(this.url + "/tms", request, new HttpRequestConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @return translation memory
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemory> getTm(Long tmId) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.get(this.url + "/tms/" + tmId, new HttpRequestConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTm(Long tmId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tms/" + tmId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param tmId translation memory identifier
     * @param request request object
     * @return updated translation memory
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemory> editTm(Long tmId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.patch(this.url + "/tms/" + tmId, request, new HttpRequestConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.segments.clear" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.segments.clear" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void clearTm(Long tmId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/tms/%d/segments", this.url, tmId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param tmId translation memory identifier
     * @param request request object
     * @return export status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.exports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.exports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemoryExportStatus> exportTm(Long tmId, TranslationMemoryExportRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryExportStatusResponseObject translationMemoryExportStatusResponseObject = this.httpClient.post(this.url + "/tms/" + tmId + "/exports", request, new HttpRequestConfig(), TranslationMemoryExportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryExportStatusResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @param exportId export identifier
     * @return export status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.exports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.exports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemoryExportStatus> checkTmExportStatus(Long tmId, String exportId) throws HttpException, HttpBadRequestException {
        TranslationMemoryExportStatusResponseObject translationMemoryExportStatusResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/exports/" + exportId, new HttpRequestConfig(), TranslationMemoryExportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryExportStatusResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @param exportId export identifier
     * @return download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.exports.download.download" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.exports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadTm(Long tmId, String exportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/exports/" + exportId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @param request request object
     * @return import status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.imports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.imports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemoryImportStatus> importTm(Long tmId, TranslationMemoryImportRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryImportStatusResponseObject translationMemoryImportStatusResponseObject = this.httpClient.post(this.url + "/tms/" + tmId + "/imports", request, new HttpRequestConfig(), TranslationMemoryImportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryImportStatusResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @param importId import identifier
     * @return import status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.tms.imports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.tms.imports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TranslationMemoryImportStatus> checkTmImportStatus(Long tmId, String importId) throws HttpException, HttpBadRequestException {
        TranslationMemoryImportStatusResponseObject translationMemoryImportStatusResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/imports/" + importId, new HttpRequestConfig(), TranslationMemoryImportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryImportStatusResponseObject.getData());
    }
}
