package com.crowdin.client.stringtranslations;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.stringtranslations.model.*;

import java.util.Map;
import java.util.Optional;

public class StringTranslationsApi extends CrowdinApi {
    public StringTranslationsApi(Credentials credentials) {
        super(credentials);
    }

    public StringTranslationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId     project identifier
     * @param fileId        file identifier
     * @param stringId      string identifier
     * @param languageId    language identifier
     * @param translationId translation identifier
     * @param limit         maximum number of items to retrieve (default 25)
     * @param offset        starting offset in the collection (default 0)
     * @return list of approvals
     */
    public ResponseList<Approval> listTranslationApprovals(Long projectId, Long fileId, Long stringId, String languageId, Long translationId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "fileId", Optional.ofNullable(fileId),
                "stringId", Optional.ofNullable(stringId),
                "languageId", Optional.ofNullable(languageId),
                "translationId", Optional.ofNullable(translationId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ApprovalResponseList approvalResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/approvals", new HttpRequestConfig(queryParams), ApprovalResponseList.class);
        return ApprovalResponseList.to(approvalResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request body
     * @return newly created approval
     */
    public ResponseObject<Approval> addApproval(Long projectId, AddApprovalRequest request) throws HttpException, HttpBadRequestException {
        ApprovalResponseObject approvalResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/approvals", request, new HttpRequestConfig(), ApprovalResponseObject.class);
        return ResponseObject.of(approvalResponseObject.getData());
    }

    /**
     * @param projectId  project identifier
     * @param approvalId approval identifier
     * @return approval
     */
    public ResponseObject<Approval> getApproval(Long projectId, Long approvalId) throws HttpException, HttpBadRequestException {
        ApprovalResponseObject storageResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/approvals/" + approvalId, new HttpRequestConfig(), ApprovalResponseObject.class);
        return ResponseObject.of(storageResponseObject.getData());
    }

    /**
     * @param projectId  project identifier
     * @param approvalId approval identifier
     */
    public void removeApproval(Long projectId, Long approvalId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/approvals/" + approvalId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param languageId language identifier
     * @param stringIds filter translations b stringIds
     * @param fileId filter translations by file identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of language translations
     */
    public ResponseList<LanguageTranslations> listLanguageTranslations(Long projectId, String languageId, String stringIds, String labelIds, Long fileId, String croql, Integer denormalizePlaceholders, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/languages/%s/translations", this.url, projectId, languageId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "stringIds", Optional.ofNullable(stringIds),
            "labelIds", Optional.ofNullable(labelIds),
            "fileId", Optional.ofNullable(fileId),
            "croql", Optional.ofNullable(croql),
            "denormalizePlaceholders", Optional.ofNullable(denormalizePlaceholders),
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset)
        );
        LanguageTranslationsResponseList languageTranslationsResponseList = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), LanguageTranslationsResponseList.class);
        return LanguageTranslationsResponseList.to(languageTranslationsResponseList);
    }

    /**
     * @param projectId  project identifier
     * @param stringId   string identifier
     * @param languageId language identifier
     * @param limit      maximum number of items to retrieve (default 25)
     * @param offset     starting offset in the collection (default 0)
     * @return list of string translations
     */
    public ResponseList<StringTranslation> listStringTranslations(Long projectId, Long stringId, String languageId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(stringId),
                "languageId", Optional.ofNullable(languageId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        StringTranslationResponseList stringTranslationResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/translations", new HttpRequestConfig(queryParams), StringTranslationResponseList.class);
        return StringTranslationResponseList.to(stringTranslationResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request body
     * @return newly created translation
     */
    public ResponseObject<StringTranslation> addTranslation(Long projectId, AddStringTranslationRequest request) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/translations", request, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId  project identifier
     * @param stringId   string identifier
     * @param languageId language identifier
     */
    public void deleteStringTranslations(Long projectId, Long stringId, String languageId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(stringId),
                "languageId", Optional.ofNullable(languageId)
        );
        this.httpClient.get(this.url + "/projects/" + projectId + "/translations", new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param projectId     project identifier
     * @param translationId translation identifier
     * @return string translation
     */
    public ResponseObject<StringTranslation> getStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/translations/" + translationId, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId     project identifier
     * @param translationId translation identifier
     */
    public void deleteStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/translations/" + translationId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId     project identifier
     * @param translationId translation identifier
     * @return string translation
     */
    public ResponseObject<StringTranslation> restoreStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.put(this.url + "/projects/" + projectId + "/translations/" + translationId, null, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId     project identifier
     * @param stringId      string identifier
     * @param languageId    language identifier
     * @param translationId translation identifier
     * @param limit         maximum number of items to retrieve (default 25)
     * @param offset        starting offset in the collection (default 0)
     * @return list of votes
     */
    public ResponseList<Vote> listTranslationVotes(Long projectId, Long stringId, String languageId, Long translationId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(stringId),
                "languageId", Optional.ofNullable(languageId),
                "translationId", Optional.ofNullable(translationId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        VoteResponseList voteResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/votes", new HttpRequestConfig(queryParams), VoteResponseList.class);
        return VoteResponseList.to(voteResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request body
     * @return newly created vote
     */
    public ResponseObject<Vote> addVote(Long projectId, AddVoteRequest request) throws HttpException, HttpBadRequestException {
        VoteResponseObject voteResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/votes", request, new HttpRequestConfig(), VoteResponseObject.class);
        return ResponseObject.of(voteResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param voteId    vote identifier
     * @return vote
     */
    public ResponseObject<Vote> getVote(Long projectId, Long voteId) throws HttpException, HttpBadRequestException {
        VoteResponseObject voteResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/votes/" + voteId, new HttpRequestConfig(), VoteResponseObject.class);
        return ResponseObject.of(voteResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param voteId    vote identifier
     */
    public void cancelVote(Long projectId, Long voteId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/votes/" + voteId, new HttpRequestConfig(), Void.class);
    }
}
