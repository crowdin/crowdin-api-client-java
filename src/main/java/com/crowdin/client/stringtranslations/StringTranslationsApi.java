package com.crowdin.client.stringtranslations;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
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
     * @param projectId project identifier
     * @param request request body
     * @return align-translation response
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.alignment.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.alignment.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public AlignTranslationResponse alignTranslation(Long projectId, AlignTranslationRequest request) {
        String url = this.url + "/projects/" + projectId + "/translations/alignment";
        return this.httpClient.post(url, request, new HttpRequestConfig(), AlignTranslationResponse.class);
    }

    /**
     * @param projectId project identifier
     * @param fileId file identifier
     * @param stringId string identifier
     * @param languageId language identifier
     * @param translationId translation identifier
     * @param labelIds filter approvals by labelIds
     * @param excludeLabelIds exclude approvals by labelIds
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of approvals
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.approvals.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.approvals.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Approval> listTranslationApprovals(Long projectId, Long fileId, Long stringId, String languageId, Long translationId, String labelIds, String excludeLabelIds, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListTranslationApprovalsOptions options = new ListTranslationApprovalsOptions();
        options.setFileId(fileId);
        options.setStringId(stringId);
        options.setLanguageId(languageId);
        options.setTranslationId(translationId);
        options.setLabelIds(labelIds);
        options.setExcludeLabelIds(excludeLabelIds);
        options.setLimit(limit);
        options.setOffset(offset);
        return listTranslationApprovals(projectId, options);
    }

    public ResponseList<Approval> listTranslationApprovals(Long projectId, ListTranslationApprovalsOptions options) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "orderBy", Optional.ofNullable(options.getOrderBy()),
                "fileId", Optional.ofNullable(options.getFileId()),
                "labelIds", Optional.ofNullable(options.getLabelIds()),
                "excludeLabelIds", Optional.ofNullable(options.getExcludeLabelIds()),
                "stringId", Optional.ofNullable(options.getStringId()),
                "languageId", Optional.ofNullable(options.getLanguageId()),
                "translationId", Optional.ofNullable(options.getTranslationId()),
                "limit", Optional.ofNullable(options.getLimit()),
                "offset", Optional.ofNullable(options.getOffset())
        );
        ApprovalResponseList approvalResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/approvals", new HttpRequestConfig(queryParams), ApprovalResponseList.class);
        return ApprovalResponseList.to(approvalResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request body
     * @return newly created approval
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.approvals.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.approvals.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Approval> addApproval(Long projectId, AddApprovalRequest request) throws HttpException, HttpBadRequestException {
        ApprovalResponseObject approvalResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/approvals", request, new HttpRequestConfig(), ApprovalResponseObject.class);
        return ResponseObject.of(approvalResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param approvalId approval identifier
     * @return approval
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.approvals.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.approvals.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Approval> getApproval(Long projectId, Long approvalId) throws HttpException, HttpBadRequestException {
        ApprovalResponseObject storageResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/approvals/" + approvalId, new HttpRequestConfig(), ApprovalResponseObject.class);
        return ResponseObject.of(storageResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param approvalId approval identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.approvals.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.approvals.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void removeApproval(Long projectId, Long approvalId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/approvals/" + approvalId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param languageId language identifier
     * @param stringIds filter translations by stringIds
     * @param labelIds filter translations by labelIds
     * @param fileId filter translations by file identifier
     * @param branchId filter translations by branchId
     * @param directoryId filter translations by directoryId
     * @param croql filter translations by croql
     * @param denormalizePlaceholders enable denormalize placeholders
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of language translations
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.languages.translations.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.languages.translations.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<LanguageTranslations> listLanguageTranslations(Long projectId, String languageId, String stringIds, String labelIds, Long fileId, Long branchId, Long directoryId, String croql, Integer denormalizePlaceholders, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListLanguageTranslationsOptions options = new ListLanguageTranslationsOptions();
        options.setStringIds(stringIds);
        options.setLabelIds(labelIds);
        options.setFileId(fileId);
        options.setBranchId(branchId);
        options.setDirectoryId(directoryId);
        options.setCroql(croql);
        options.setDenormalizePlaceholders(BooleanInt.fromInt(denormalizePlaceholders));
        options.setLimit(limit);
        options.setOffset(offset);
        return listLanguageTranslations(projectId, languageId, options);
    }

    public ResponseList<LanguageTranslations> listLanguageTranslations(Long projectId, String languageId, ListLanguageTranslationsOptions options) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/languages/%s/translations", this.url, projectId, languageId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "stringIds", Optional.ofNullable(options.getStringIds()),
            "labelIds", Optional.ofNullable(options.getLabelIds()),
            "fileId", Optional.ofNullable(options.getFileId()),
            "branchId", Optional.ofNullable(options.getBranchId()),
            "directoryId", Optional.ofNullable(options.getDirectoryId()),
            "passedWorkflow", Optional.ofNullable(options.getPassedWorkflow()),
            "minApprovalCount", Optional.ofNullable(options.getMinApprovalCount()),
            "croql", Optional.ofNullable(options.getCroql()),
            "denormalizePlaceholders", Optional.ofNullable(options.getDenormalizePlaceholders()),
            "limit", Optional.ofNullable(options.getLimit()),
            "offset", Optional.ofNullable(options.getOffset())
        );
        LanguageTranslationsResponseList languageTranslationsResponseList = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), LanguageTranslationsResponseList.class);
        return LanguageTranslationsResponseList.to(languageTranslationsResponseList);
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @param languageId language identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of string translations
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StringTranslation> listStringTranslations(Long projectId, Long stringId, String languageId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListStringTranslationsOptions options = new ListStringTranslationsOptions();
        options.setStringId(stringId);
        options.setLanguageId(languageId);
        options.setLimit(limit);
        options.setOffset(offset);
        return listStringTranslations(projectId, options);
    }

    public ResponseList<StringTranslation> listStringTranslations(Long projectId, ListStringTranslationsOptions options) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(options.getStringId()),
                "languageId", Optional.ofNullable(options.getLanguageId()),
                "orderBy", Optional.ofNullable(options.getOrderBy()),
                "denormalizePlaceholders", Optional.ofNullable(options.getDenormalizePlaceholders()),
                "limit", Optional.ofNullable(options.getLimit()),
                "offset", Optional.ofNullable(options.getOffset())
        );
        StringTranslationResponseList stringTranslationResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/translations", new HttpRequestConfig(queryParams), StringTranslationResponseList.class);
        return StringTranslationResponseList.to(stringTranslationResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request body
     * @return newly created translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringTranslation> addTranslation(Long projectId, AddStringTranslationRequest request) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/translations", request, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @param languageId language identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.deleteMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteStringTranslations(Long projectId, Long stringId, String languageId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(stringId),
                "languageId", Optional.ofNullable(languageId)
        );
        this.httpClient.get(this.url + "/projects/" + projectId + "/translations", new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param translationId translation identifier
     * @return string translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringTranslation> getStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/translations/" + translationId, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param translationId translation identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/translations/" + translationId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param translationId translation identifier
     * @return string translation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.translations.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.translations.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringTranslation> restoreStringTranslation(Long projectId, Long translationId) throws HttpException, HttpBadRequestException {
        StringTranslationResponseObject stringTranslationResponseObject = this.httpClient.put(this.url + "/projects/" + projectId + "/translations/" + translationId, null, new HttpRequestConfig(), StringTranslationResponseObject.class);
        return ResponseObject.of(stringTranslationResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @param languageId language identifier
     * @param translationId translation identifier
     * @param labelIds filter votes by labelIds
     * @param excludeLabelIds exclude votes by labelIds
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of votes
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.votes.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.votes.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Vote> listTranslationVotes(Long projectId, Long stringId, String languageId, Long translationId, String labelIds, String excludeLabelIds, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListTranslationVotesOptions options = new ListTranslationVotesOptions();
        options.setStringId(stringId);
        options.setLanguageId(languageId);
        options.setTranslationId(translationId);
        options.setLabelIds(labelIds);
        options.setExcludeLabelIds(excludeLabelIds);
        options.setLimit(limit);
        options.setOffset(offset);
        return listTranslationVotes(projectId, options);
    }

    public ResponseList<Vote> listTranslationVotes(Long projectId, ListTranslationVotesOptions options) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "stringId", Optional.ofNullable(options.getStringId()),
                "languageId", Optional.ofNullable(options.getLanguageId()),
                "translationId", Optional.ofNullable(options.getTranslationId()),
                "fileId", Optional.ofNullable(options.getFileId()),
                "labelIds", Optional.ofNullable(options.getLabelIds()),
                "excludeLabelIds", Optional.ofNullable(options.getExcludeLabelIds()),
                "limit", Optional.ofNullable(options.getLimit()),
                "offset", Optional.ofNullable(options.getOffset())
        );
        VoteResponseList voteResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/votes", new HttpRequestConfig(queryParams), VoteResponseList.class);
        return VoteResponseList.to(voteResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request body
     * @return newly created vote
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.votes.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.votes.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Vote> addVote(Long projectId, AddVoteRequest request) throws HttpException, HttpBadRequestException {
        VoteResponseObject voteResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/votes", request, new HttpRequestConfig(), VoteResponseObject.class);
        return ResponseObject.of(voteResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param voteId vote identifier
     * @return vote
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.votes.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.votes.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Vote> getVote(Long projectId, Long voteId) throws HttpException, HttpBadRequestException {
        VoteResponseObject voteResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/votes/" + voteId, new HttpRequestConfig(), VoteResponseObject.class);
        return ResponseObject.of(voteResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param voteId vote identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.votes.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.votes.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void cancelVote(Long projectId, Long voteId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/votes/" + voteId, new HttpRequestConfig(), Void.class);
    }
}
