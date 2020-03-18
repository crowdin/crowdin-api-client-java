package com.crowdin.client.languages;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.languages.model.AddCustomLanguageRequest;
import com.crowdin.client.languages.model.Language;
import com.crowdin.client.languages.model.LanguageResponseList;
import com.crowdin.client.languages.model.LanguageResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LanguagesApi extends CrowdinApi {
    public LanguagesApi(Credentials credentials) {
        super(credentials);
    }

    public LanguagesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param limit  maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of languages
     */
    public ResponseList<Language> listSupportedLanguages(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        LanguageResponseList languageResponseList = this.httpClient.get(this.url + "/languages", new HttpConfig(queryParams), LanguageResponseList.class);
        return LanguageResponseList.to(languageResponseList);
    }

    /**
     * @param request request object
     * @return newly created language
     */
    public ResponseObject<Language> addCustomLanguage(AddCustomLanguageRequest request) throws HttpException, HttpBadRequestException {
        LanguageResponseObject projectResponseObject = this.httpClient.post(this.url + "/languages", request, new HttpConfig(), LanguageResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }

    /**
     * @param languageId language identifier
     * @return language
     */
    public ResponseObject<Language> getLanguage(String languageId) throws HttpException, HttpBadRequestException {
        LanguageResponseObject languageResponseObject = this.httpClient.get(this.url + "/languages" + languageId, new HttpConfig(), LanguageResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }

    /**
     * @param languageId project identifier
     */
    public void deleteLanguage(String languageId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/languages" + languageId, new HttpConfig(), Void.class);
    }

    /**
     * @param languageId language identifier
     * @param request    request object
     * @return updated language
     */
    public ResponseObject<Language> editProject(String languageId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        LanguageResponseObject languageResponseObject = this.httpClient.patch(this.url + "/languages" + languageId, request, new HttpConfig(), LanguageResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }
}
