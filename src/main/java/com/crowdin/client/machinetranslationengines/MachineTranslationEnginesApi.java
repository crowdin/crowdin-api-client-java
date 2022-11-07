package com.crowdin.client.machinetranslationengines;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.machinetranslationengines.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MachineTranslationEnginesApi extends CrowdinApi {
    public MachineTranslationEnginesApi(Credentials credentials) {
        super(credentials);
    }

    public MachineTranslationEnginesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param groupId group identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of machine translations
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.mts.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<MachineTranslation> listMts(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        MachineTranslationResponseList machineTranslationResponseList = this.httpClient.get(this.url + "/mts", new HttpRequestConfig(queryParams), MachineTranslationResponseList.class);
        return MachineTranslationResponseList.to(machineTranslationResponseList);
    }

    /**
     * @param request request object
     * @return newly created machine translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<MachineTranslation> addMt(AddMachineTranslationRequest request) throws HttpException, HttpBadRequestException {
        MachineTranslationResponseObject machineTranslationResponseObject = this.httpClient.post(this.url + "/mts", request, new HttpRequestConfig(), MachineTranslationResponseObject.class);
        return ResponseObject.of(machineTranslationResponseObject.getData());
    }

    /**
     * @param mtId machine translation identifier
     * @return machine translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.mts.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<MachineTranslation> getMt(Long mtId) throws HttpException, HttpBadRequestException {
        MachineTranslationResponseObject machineTranslationResponseObject = this.httpClient.get(this.url + "/mts/" + mtId, new HttpRequestConfig(), MachineTranslationResponseObject.class);
        return ResponseObject.of(machineTranslationResponseObject.getData());
    }

    /**
     * @param mtId machine translation identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteMt(Long mtId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/mts/" + mtId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param mtId machine translation identifier
     * @param request request object
     * @return updated machine translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<MachineTranslation> editMt(Long mtId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        MachineTranslationResponseObject machineTranslationResponseObject = this.httpClient.patch(this.url + "/mts/" + mtId, request, new HttpRequestConfig(), MachineTranslationResponseObject.class);
        return ResponseObject.of(machineTranslationResponseObject.getData());
    }

    /**
     * @param mtId machine translation identifier
     * @param request request object
     * @return mt translate response
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.mts.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.mts.translations.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<MtTranslateResponse> translateViaMt(Long mtId, MtTranslateRequest request) throws HttpException, HttpBadRequestException {
        MtTranslateResponseObject mtTranslateResponseObject = this.httpClient.post(this.url + "/mts/" + mtId + "/translations", request, new HttpRequestConfig(), MtTranslateResponseObject.class);
        return ResponseObject.of(mtTranslateResponseObject.getData());
    }
}
