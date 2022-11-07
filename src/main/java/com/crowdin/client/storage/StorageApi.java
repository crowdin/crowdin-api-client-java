package com.crowdin.client.storage;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.storage.model.Storage;
import com.crowdin.client.storage.model.StorageResponseList;
import com.crowdin.client.storage.model.StorageResponseObject;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class StorageApi extends CrowdinApi {

    public StorageApi(Credentials credentials) {
        super(credentials);
    }

    public StorageApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of storages
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.storages.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.storages.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Storage> listStorages(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        StorageResponseList storageResponseList = this.httpClient.get(this.url + "/storages", new HttpRequestConfig(queryParams), StorageResponseList.class);
        return StorageResponseList.to(storageResponseList);
    }

    /**
     * @param fileName file name
     * @param content file content
     * @return newly created storage
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.storages.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.storages.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Storage> addStorage(String fileName, String content) throws HttpException, HttpBadRequestException {
        StorageResponseObject storageResponseObject = this.httpClient.post(this.url + "/storages", content, new HttpRequestConfig(
                Collections.emptyMap(),
                Collections.singletonMap("Crowdin-API-FileName", fileName)
        ), StorageResponseObject.class);
        return ResponseObject.of(storageResponseObject.getData());
    }

    /**
     * @param fileName file name
     * @param content file content
     * @return newly created storage
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.storages.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.storages.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Storage> addStorage(String fileName, InputStream content) throws HttpException, HttpBadRequestException {
        StorageResponseObject storageResponseObject = this.httpClient.post(this.url + "/storages", content, new HttpRequestConfig(
                Collections.emptyMap(),
                Collections.singletonMap("Crowdin-API-FileName", fileName)
        ), StorageResponseObject.class);
        return ResponseObject.of(storageResponseObject.getData());
    }

    /**
     * @param storageId storage identifier
     * @return storage
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.storages.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.storages.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Storage> getStorage(Long storageId) throws HttpException, HttpBadRequestException {
        StorageResponseObject storageResponseObject = this.httpClient.get(this.url + "/storages/" + storageId, new HttpRequestConfig(), StorageResponseObject.class);
        return ResponseObject.of(storageResponseObject.getData());
    }

    /**
     * @param storageId storage identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.storages.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.storages.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteStorage(Long storageId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/storages/" + storageId, new HttpRequestConfig(), Void.class);
    }

}
