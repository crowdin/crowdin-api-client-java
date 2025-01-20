package com.crowdin.client.glossaries;

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
import com.crowdin.client.glossaries.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GlossariesApi extends CrowdinApi {
    public GlossariesApi(Credentials credentials) {
        super(credentials);
    }

    public GlossariesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }


    /**
     * @param projectId  project identifier
     * @param request    request object
     * @return list of search results
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.glossaries.concordance.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.glossaries.concordance.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SearchConcordance> searchConcordance(Long projectId, SearchConcordanceRequest request) {
        SearchConcordanceResponseList searchConcordanceResponseList =
                this.httpClient.post(this.url + "/projects/" + projectId + "/glossaries/concordance", request, new HttpRequestConfig(), SearchConcordanceResponseList.class);
        return SearchConcordanceResponseList.of(searchConcordanceResponseList);
    }

    /**
     * @param glossaryId glossary identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of concepts
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.concepts.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.concepts.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Concept> listConcepts(Long glossaryId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListConceptsParams params = new ListConceptsParams();
        params.setLimit(limit);
        params.setOffset(offset);
        return listConcepts(glossaryId, params);
    }

    public ResponseList<Concept> listConcepts(Long glossaryId, ListConceptsParams params) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "orderBy", Optional.ofNullable(params.getOrderBy()),
                "limit", Optional.ofNullable(params.getLimit()),
                "offset", Optional.ofNullable(params.getOffset())
        );
        ConceptResponseList conceptResponseList = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/concepts", new HttpRequestConfig(queryParams), ConceptResponseList.class);
        return ConceptResponseList.to(conceptResponseList);
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     * @return concept
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.concepts.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.concepts.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Concept> getConcept(Long glossaryId, Long conceptId) throws HttpException, HttpBadRequestException {
        ConceptResponseObject conceptResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, new HttpRequestConfig(), ConceptResponseObject.class);
        return ResponseObject.of(conceptResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     * @param request request object
     * @return updated concept
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.concepts.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.concepts.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Concept> updateConcept(Long glossaryId, Long conceptId, Concept request) throws HttpException, HttpBadRequestException {
        ConceptResponseObject conceptResponseObject = this.httpClient.put(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, request, new HttpRequestConfig(), ConceptResponseObject.class);
        return ResponseObject.of(conceptResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.concepts.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.concepts.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteConcept(Long glossaryId, Long conceptId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param groupId group identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of glossaries
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Glossary> listGlossaries(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListGlossariesParams params = new ListGlossariesParams();
        params.setGroupId(groupId);
        params.setLimit(limit);
        params.setOffset(offset);
        return listGlossaries(params);
    }

    public ResponseList<Glossary> listGlossaries(ListGlossariesParams params) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(params.getGroupId()),
                "userId", Optional.ofNullable(params.getUserId()),
                "limit", Optional.ofNullable(params.getLimit()),
                "offset", Optional.ofNullable(params.getOffset())
        );
        GlossaryResponseList glossaryResponseList = this.httpClient.get(this.url + "/glossaries", new HttpRequestConfig(queryParams), GlossaryResponseList.class);
        return GlossaryResponseList.to(glossaryResponseList);
    }

    /**
     * @param request request object
     * @return newly created glossary
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Glossary> addGlossary(AddGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.post(this.url + "/glossaries", request, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @return glossary
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Glossary> getGlossary(Long glossaryId) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteGlossary(Long glossaryId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return updated glossary
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Glossary> editGlossary(Long glossaryId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.patch(this.url + "/glossaries/" + glossaryId, request, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return export status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.exports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.exports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GlossaryExportStatus> exportGlossary(Long glossaryId, ExportGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryExportStatusResponseObject glossaryExportStatusResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/exports", request, new HttpRequestConfig(), GlossaryExportStatusResponseObject.class);
        return ResponseObject.of(glossaryExportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param exportId export identifier
     * @return export status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.exports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.exports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GlossaryExportStatus> checkGlossaryExportStatus(Long glossaryId, String exportId) throws HttpException, HttpBadRequestException {
        GlossaryExportStatusResponseObject glossaryExportStatusResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId, new HttpRequestConfig(), GlossaryExportStatusResponseObject.class);
        return ResponseObject.of(glossaryExportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param exportId export identifier
     * @return download link
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.exports.download.download" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.exports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadGlossary(Long glossaryId, String exportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return import status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.imports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.imports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GlossaryImportStatus> importGlossary(Long glossaryId, ImportGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryImportStatusResponseObject glossaryImportStatusResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/imports", request, new HttpRequestConfig(), GlossaryImportStatusResponseObject.class);
        return ResponseObject.of(glossaryImportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param importId import identifier
     * @return import status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.imports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.imports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GlossaryImportStatus> checkGlossaryImportStatus(Long glossaryId, String importId) throws HttpException, HttpBadRequestException {
        GlossaryImportStatusResponseObject glossaryImportStatusResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/imports/" + importId, new HttpRequestConfig(), GlossaryImportStatusResponseObject.class);
        return ResponseObject.of(glossaryImportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param userId user identifier
     * @param languageId language identifier
     * @param conceptId concept identifier
     * @param translationOfTermId term identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of terms
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Term> listTerms(Long glossaryId, Long userId, String languageId, Long conceptId, @Deprecated Long translationOfTermId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListTermsParams params = new ListTermsParams();
        params.setUserId(userId);
        params.setLanguageId(languageId);
        params.setConceptId(conceptId);
        params.setTranslationOfTermId(translationOfTermId);
        params.setLimit(limit);
        params.setOffset(offset);
        return listTerms(glossaryId, params);
    }

    public ResponseList<Term> listTerms(Long glossaryId, ListTermsParams params) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "orderBy", Optional.ofNullable(params.getOrderBy()),
                "userId", Optional.ofNullable(params.getUserId()),
                "languageId", Optional.ofNullable(params.getLanguageId()),
                "conceptId", Optional.ofNullable(params.getConceptId()),
                "translationOfTermId", Optional.ofNullable(params.getTranslationOfTermId()),
                "croql", Optional.ofNullable(params.getCroql()),
                "limit", Optional.ofNullable(params.getLimit()),
                "offset", Optional.ofNullable(params.getOffset())
        );
        TermResponseList termResponseList = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/terms", new HttpRequestConfig(queryParams), TermResponseList.class);
        return TermResponseList.to(termResponseList);
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return newly created term
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Term> addTerm(Long glossaryId, AddTermRequest request) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/terms", request, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param languageId language identifier
     * @param conceptId concept identifier
     * @param translationOfTermId translationOfTerm identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.deleteMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void clearGlossary(Long glossaryId, String languageId, Long conceptId, @Deprecated Long translationOfTermId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "languageId", Optional.ofNullable(languageId),
                "conceptId", Optional.ofNullable(conceptId),
                "translationOfTermId", Optional.ofNullable(translationOfTermId)
        );
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId + "/terms", new HttpRequestConfig(queryParams), Void.class);
    }

    /**
     * @param glossaryId glossary identifier
     * @param termId term identifier
     * @return term
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Term> getTerm(Long glossaryId, Long termId) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param termId term identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTerm(Long glossaryId, Long termId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param glossaryId glossary identifier
     * @param termId term identifier
     * @param request request object
     * @return updated term
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.glossaries.terms.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.glossaries.terms.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Term> editTerm(Long glossaryId, Long termId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.patch(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, request, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }
}
