package com.crowdin.client.dictionaries;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.dictionaries.model.Dictionary;
import com.crowdin.client.dictionaries.model.DictionaryResponseList;
import com.crowdin.client.dictionaries.model.DictionaryResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DictionariesApi extends CrowdinApi {

    public DictionariesApi(Credentials credentials) {
        super(credentials);
    }

    public DictionariesApi(Credentials credentials, ClientConfig clientConfig) throws HttpException, HttpBadRequestException {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param languageIds language identifiers
     * @return list of dictionaries
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.dictionaries.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.dictionaries.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Dictionary> listDictionaries(Long projectId, String languageIds) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/dictionaries", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "languageIds", Optional.ofNullable(languageIds)
        );
        DictionaryResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), DictionaryResponseList.class);
        return DictionaryResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param languageId language identifier
     * @param request request object
     * @return updated dictionary
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.dictionaries.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.dictionaries.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Dictionary> editDictionary(Long projectId, String languageId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/dictionaries/%s", this.url, projectId, languageId);
        DictionaryResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), DictionaryResponseObject.class);
        return ResponseObject.of(response.getData());
    }


}
