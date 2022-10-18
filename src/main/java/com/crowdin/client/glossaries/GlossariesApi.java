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
     * @param glossaryId glossary identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of concepts
     */
    public ResponseList<Concept> listConcepts(Long glossaryId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ConceptResponseList conceptResponseList = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/concepts", new HttpRequestConfig(queryParams), ConceptResponseList.class);
        return ConceptResponseList.to(conceptResponseList);
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     * @return concept
     */
    public ResponseObject<Concept> getConcept(Long glossaryId, Long conceptId) throws HttpException, HttpBadRequestException {
        ConceptResponseObject conceptResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, new HttpRequestConfig(), ConceptResponseObject.class);
        return ResponseObject.of(conceptResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     * @return updated concept
     */
    public ResponseObject<Concept> updateConcept(Long glossaryId, Long conceptId, Concept request) throws HttpException, HttpBadRequestException {
        ConceptResponseObject conceptResponseObject = this.httpClient.put(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, request, new HttpRequestConfig(), ConceptResponseObject.class);
        return ResponseObject.of(conceptResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param conceptId concept identifier
     */
    public void deleteConcept(Long glossaryId, Long conceptId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param groupId group identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of glossaries
     */
    public ResponseList<Glossary> listGlossaries(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        GlossaryResponseList glossaryResponseList = this.httpClient.get(this.url + "/glossaries", new HttpRequestConfig(queryParams), GlossaryResponseList.class);
        return GlossaryResponseList.to(glossaryResponseList);
    }

    /**
     * @param request request object
     * @return newly created glossary
     */
    public ResponseObject<Glossary> addGlossary(AddGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.post(this.url + "/glossaries", request, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @return glossary
     */
    public ResponseObject<Glossary> getGlossary(Long glossaryId) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     */
    public void deleteGlossary(Long glossaryId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return updated glossary
     */
    public ResponseObject<Glossary> editGlossary(Long glossaryId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        GlossaryResponseObject glossaryResponseObject = this.httpClient.patch(this.url + "/glossaries/" + glossaryId, request, new HttpRequestConfig(), GlossaryResponseObject.class);
        return ResponseObject.of(glossaryResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return export status
     */
    public ResponseObject<GlossaryExportStatus> exportGlossary(Long glossaryId, ExportGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryExportStatusResponseObject glossaryExportStatusResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/exports", request, new HttpRequestConfig(), GlossaryExportStatusResponseObject.class);
        return ResponseObject.of(glossaryExportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param exportId export identifier
     * @return export status
     */
    public ResponseObject<GlossaryExportStatus> checkGlossaryExportStatus(Long glossaryId, String exportId) throws HttpException, HttpBadRequestException {
        GlossaryExportStatusResponseObject glossaryExportStatusResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId, new HttpRequestConfig(), GlossaryExportStatusResponseObject.class);
        return ResponseObject.of(glossaryExportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param exportId export identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadGlossary(Long glossaryId, String exportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return import status
     */
    public ResponseObject<GlossaryImportStatus> importGlossary(Long glossaryId, ImportGlossaryRequest request) throws HttpException, HttpBadRequestException {
        GlossaryImportStatusResponseObject glossaryImportStatusResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/imports", request, new HttpRequestConfig(), GlossaryImportStatusResponseObject.class);
        return ResponseObject.of(glossaryImportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param importId import identifier
     * @return import status
     */
    public ResponseObject<GlossaryImportStatus> checkGlossaryImportStatus(Long glossaryId, String importId) throws HttpException, HttpBadRequestException {
        GlossaryImportStatusResponseObject glossaryImportStatusResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/imports/" + importId, new HttpRequestConfig(), GlossaryImportStatusResponseObject.class);
        return ResponseObject.of(glossaryImportStatusResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param userId user identifier
     * @param languageId language identifier
     * @param translationOfTermId term identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of terms
     */
    public ResponseList<Term> listTerms(Long glossaryId, Long userId, String languageId, Long conceptId, @Deprecated Long translationOfTermId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "userId", Optional.ofNullable(userId),
                "languageId", Optional.ofNullable(languageId),
                "conceptId", Optional.ofNullable(conceptId),
                "translationOfTermId", Optional.ofNullable(translationOfTermId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TermResponseList termResponseList = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/terms", new HttpRequestConfig(queryParams), TermResponseList.class);
        return TermResponseList.to(termResponseList);
    }

    /**
     * @param glossaryId glossary identifier
     * @param request request object
     * @return newly created term
     */
    public ResponseObject<Term> addTerm(Long glossaryId, AddTermRequest request) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.post(this.url + "/glossaries/" + glossaryId + "/terms", request, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param languageId language identifier
     * @param translationOfTermId translationOfTerm identifier
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
     */
    public ResponseObject<Term> getTerm(Long glossaryId, Long termId) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.get(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }

    /**
     * @param glossaryId glossary identifier
     * @param termId term identifier
     */
    public void deleteTerm(Long glossaryId, Long termId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param glossaryId glossary identifier
     * @param termId term identifier
     * @param request request object
     * @return updated term
     */
    public ResponseObject<Term> editTerm(Long glossaryId, Long termId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TermResponseObject termResponseObject = this.httpClient.patch(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, request, new HttpRequestConfig(), TermResponseObject.class);
        return ResponseObject.of(termResponseObject.getData());
    }
}
