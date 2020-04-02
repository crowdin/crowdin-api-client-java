package com.crowdin.client.translationstatus;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.translationstatus.model.Category;
import com.crowdin.client.translationstatus.model.FileProgress;
import com.crowdin.client.translationstatus.model.FileProgressResponseList;
import com.crowdin.client.translationstatus.model.LanguageProgress;
import com.crowdin.client.translationstatus.model.LanguageProgressResponseList;
import com.crowdin.client.translationstatus.model.QaCheck;
import com.crowdin.client.translationstatus.model.QaCheckResponseList;
import com.crowdin.client.translationstatus.model.Validation;

import java.util.Map;
import java.util.Optional;

public class TranslationStatusApi extends CrowdinApi {
    public TranslationStatusApi(Credentials credentials) {
        super(credentials);
    }

    public TranslationStatusApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of branch progress
     */
    public ResponseList<LanguageProgress> getBranchProgress(Long projectId, Long branchId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        LanguageProgressResponseList languageProgressResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId + "/languages/progress", new HttpConfig(queryParams), LanguageProgressResponseList.class);
        return LanguageProgressResponseList.to(languageProgressResponseList);
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @return list of directory progress
     */
    public ResponseList<LanguageProgress> getDirectoryProgress(Long projectId, Long directoryId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        LanguageProgressResponseList languageProgressResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/directories/" + directoryId + "/languages/progress", new HttpConfig(queryParams), LanguageProgressResponseList.class);
        return LanguageProgressResponseList.to(languageProgressResponseList);
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of file progress
     */
    public ResponseList<LanguageProgress> getFileProgress(Long projectId, Long fileId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        LanguageProgressResponseList languageProgressResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/languages/progress", new HttpConfig(queryParams), LanguageProgressResponseList.class);
        return LanguageProgressResponseList.to(languageProgressResponseList);
    }

    /**
     * @param projectId  project identifier
     * @param languageId language identifier
     * @param limit      maximum number of items to retrieve (default 25)
     * @param offset     starting offset in the collection (default 0)
     * @return list of language progress
     */
    public ResponseList<FileProgress> getLanguageProgress(Long projectId, String languageId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileProgressResponseList fileProgressResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/languages/" + languageId + "/progress", new HttpConfig(queryParams), FileProgressResponseList.class);
        return FileProgressResponseList.to(fileProgressResponseList);
    }

    /**
     * @param projectId   project identifier
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @param languageIds language identifiers
     * @return list of project progress
     */
    public ResponseList<LanguageProgress> getProjectProgress(Long projectId, Integer limit, Integer offset, String languageIds) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "languageIds", Optional.ofNullable(languageIds)
        );
        LanguageProgressResponseList languageProgressResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/languages/progress", new HttpConfig(queryParams), LanguageProgressResponseList.class);
        return LanguageProgressResponseList.to(languageProgressResponseList);
    }

    /**
     * @param projectId  project identifier
     * @param limit      maximum number of items to retrieve (default 25)
     * @param offset     starting offset in the collection (default 0)
     * @param category   category
     * @param validation validation
     * @return list of qa check issues
     */
    public ResponseList<QaCheck> listQaCheckIssues(Long projectId, Integer limit, Integer offset, Category category, Validation validation) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "category", Optional.ofNullable(category),
                "validation", Optional.ofNullable(validation)
        );
        QaCheckResponseList qaCheckResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/qa-check", new HttpConfig(queryParams), QaCheckResponseList.class);
        return QaCheckResponseList.to(qaCheckResponseList);
    }
}
