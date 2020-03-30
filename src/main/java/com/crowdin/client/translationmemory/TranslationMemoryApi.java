package com.crowdin.client.translationmemory;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchOperation;
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
     * @param limit   maximum number of items to retrieve (default 25)
     * @param offset  starting offset in the collection (default 0)
     * @return list of translation memories
     */
    public ResponseList<TranslationMemory> listTms(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TranslationMemoryResponseList translationMemoryResponseList = this.httpClient.get(this.url + "/tms", new HttpConfig(queryParams), TranslationMemoryResponseList.class);
        return TranslationMemoryResponseList.to(translationMemoryResponseList);
    }

    /**
     * @param request request object
     * @return newly created translation memory
     */
    public ResponseObject<TranslationMemory> addTm(AddTranslationMemoryRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.post(this.url + "/tms", request, new HttpConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     * @return translation memory
     */
    public ResponseObject<TranslationMemory> getTm(Long tmId) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.get(this.url + "/tms/" + tmId, new HttpConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId translation memory identifier
     */
    public void deleteTm(Long tmId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/tms/" + tmId, new HttpConfig(), Void.class);
    }

    /**
     * @param tmId    translation memory identifier
     * @param request request object
     * @return updated translation memory
     */
    public ResponseObject<TranslationMemory> editTm(Long tmId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        TranslationMemoryResponseObject translationMemoryResponseObject = this.httpClient.patch(this.url + "/tms/" + tmId, request, new HttpConfig(), TranslationMemoryResponseObject.class);
        return ResponseObject.of(translationMemoryResponseObject.getData());
    }

    /**
     * @param tmId    translation memory identifier
     * @param request request object
     * @return export status
     */
    public ResponseObject<TranslationMemoryExportStatus> exportTm(Long tmId, TranslationMemoryExportRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryExportStatusResponseObject translationMemoryExportStatusResponseObject = this.httpClient.post(this.url + "/tms/" + tmId + "/exports", request, new HttpConfig(), TranslationMemoryExportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryExportStatusResponseObject.getData());
    }

    /**
     * @param tmId     translation memory identifier
     * @param exportId export identifier
     * @return export status
     */
    public ResponseObject<TranslationMemoryExportStatus> checkTmExportStatus(Long tmId, String exportId) throws HttpException, HttpBadRequestException {
        TranslationMemoryExportStatusResponseObject translationMemoryExportStatusResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/exports/" + exportId, new HttpConfig(), TranslationMemoryExportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryExportStatusResponseObject.getData());
    }

    /**
     * @param tmId     translation memory identifier
     * @param exportId export identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadTm(Long tmId, String exportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/exports/" + exportId + "/download", new HttpConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param tmId    translation memory identifier
     * @param request request object
     * @return import status
     */
    public ResponseObject<TranslationMemoryImportStatus> importTm(Long tmId, TranslationMemoryImportRequest request) throws HttpException, HttpBadRequestException {
        TranslationMemoryImportStatusResponseObject translationMemoryImportStatusResponseObject = this.httpClient.post(this.url + "/tms/" + tmId + "/imports", request, new HttpConfig(), TranslationMemoryImportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryImportStatusResponseObject.getData());
    }

    /**
     * @param tmId     translation memory identifier
     * @param importId import identifier
     * @return import status
     */
    public ResponseObject<TranslationMemoryImportStatus> checkTmImportStatus(Long tmId, String importId) throws HttpException, HttpBadRequestException {
        TranslationMemoryImportStatusResponseObject translationMemoryImportStatusResponseObject = this.httpClient.get(this.url + "/tms/" + tmId + "/imports/" + importId, new HttpConfig(), TranslationMemoryImportStatusResponseObject.class);
        return ResponseObject.of(translationMemoryImportStatusResponseObject.getData());
    }
}
