package com.crowdin.client.glossaries;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.Format;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.glossaries.model.AddGlossaryRequest;
import com.crowdin.client.glossaries.model.AddTermRequest;
import com.crowdin.client.glossaries.model.ExportGlossaryRequest;
import com.crowdin.client.glossaries.model.Glossary;
import com.crowdin.client.glossaries.model.GlossaryExportStatus;
import com.crowdin.client.glossaries.model.GlossaryImportStatus;
import com.crowdin.client.glossaries.model.ImportGlossaryRequest;
import com.crowdin.client.glossaries.model.PartOfSpeech;
import com.crowdin.client.glossaries.model.Term;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlossariesApiTest extends TestClient {

    private final Long glossaryId = 2L;
    private final Long groupId = 2L;
    private final Long termId = 2L;
    private final String name = "Be My Eyes iOS's Glossary";
    private final String text = "Voir";
    private final String exportId = "5ed2ce93-6d47-4402-9e66-516ca835cb20";
    private final String importId = "c050fba2-200e-4ce1-8de4-f7ba8eb58732";
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/glossaries", HttpGet.METHOD_NAME, "api/glossaries/listGlossaries.json"),
                RequestMock.build(this.url + "/glossaries", HttpPost.METHOD_NAME, "api/glossaries/addGlossaryRequest.json", "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpGet.METHOD_NAME, "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpPatch.METHOD_NAME, "api/glossaries/editGlossary.json", "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports", HttpPost.METHOD_NAME, "api/glossaries/exportGlossary.json", "api/glossaries/exportGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId, HttpGet.METHOD_NAME, "api/glossaries/exportGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId + "/download", HttpGet.METHOD_NAME, "api/glossaries/downloadLink.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/imports", HttpPost.METHOD_NAME, "api/glossaries/importGlossary.json", "api/glossaries/importGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/imports/" + importId, HttpGet.METHOD_NAME, "api/glossaries/importGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpGet.METHOD_NAME, "api/glossaries/listTerms.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpPost.METHOD_NAME, "api/glossaries/addTermRequest.json", "api/glossaries/term.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpGet.METHOD_NAME, "api/glossaries/term.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpPatch.METHOD_NAME, "api/glossaries/editTerm.json", "api/glossaries/term.json")
        );
    }

    @Test
    public void listGlossariesTest() {
        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(null, null, null);
        assertEquals(glossaryResponseList.getData().size(), 1);
        assertEquals(glossaryResponseList.getData().get(0).getData().getId(), glossaryId);
        assertEquals(glossaryResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addGlossaryTest() {
        AddGlossaryRequest request = new AddGlossaryRequest();
        request.setName(name);
        request.setGroupId(groupId);
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().addGlossary(request);
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
    }

    @Test
    public void getGlossaryTest() {
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().getGlossary(glossaryId);
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteGlossaryTest() {
        this.getGlossariesApi().deleteGlossary(glossaryId);
    }

    @Test
    public void editGlossaryTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().editGlossary(glossaryId, singletonList(request));
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
    }

    @Test
    public void exportGlossaryTest() {
        ExportGlossaryRequest request = new ExportGlossaryRequest();
        request.setFormat(Format.TBX);
        ResponseObject<GlossaryExportStatus> glossaryExportStatusResponseObject = this.getGlossariesApi().exportGlossary(glossaryId, request);
        assertEquals(glossaryExportStatusResponseObject.getData().getIdentifier(), exportId);
    }

    @Test
    public void checkGlossaryExportStatusTest() {
        ResponseObject<GlossaryExportStatus> glossaryExportStatusResponseObject = this.getGlossariesApi().checkGlossaryExportStatus(glossaryId, exportId);
        assertEquals(glossaryExportStatusResponseObject.getData().getIdentifier(), exportId);
    }

    @Test
    public void downloadGlossaryTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getGlossariesApi().downloadGlossary(glossaryId, exportId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void importGlossaryTest() {
        ImportGlossaryRequest request = new ImportGlossaryRequest();
        request.setStorageId(12L);
        request.setScheme(singletonMap("term_en", 2));
        ResponseObject<GlossaryImportStatus> glossaryImportStatusResponseObject = this.getGlossariesApi().importGlossary(glossaryId, request);
        assertEquals(glossaryImportStatusResponseObject.getData().getIdentifier(), importId);
    }

    @Test
    public void checkGlossaryImportStatusTest() {
        ResponseObject<GlossaryImportStatus> glossaryImportStatusResponseObject = this.getGlossariesApi().checkGlossaryImportStatus(glossaryId, importId);
        assertEquals(glossaryImportStatusResponseObject.getData().getIdentifier(), importId);
    }

    @Test
    public void listTermsTest() {
        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossaryId, null, null, null, null, null);
        assertEquals(termResponseList.getData().size(), 1);
        assertEquals(termResponseList.getData().get(0).getData().getId(), termId);
    }

    @Test
    public void addTermTest() {
        AddTermRequest request = new AddTermRequest();
        request.setText(text);
        request.setLanguageId("fr");
        request.setPartOfSpeech(PartOfSpeech.VERB);
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().addTerm(glossaryId, request);
        assertEquals(termResponseObject.getData().getId(), termId);
    }

    @Test
    public void clearGlossaryTest() {
        this.getGlossariesApi().clearGlossary(glossaryId, null, null);
    }

    @Test
    public void getTermTest() {
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().getTerm(glossaryId, termId);
        assertEquals(termResponseObject.getData().getId(), termId);
    }

    @Test
    public void deleteTermTest() {
        this.getGlossariesApi().deleteTerm(glossaryId, termId);
    }

    @Test
    public void editTermTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(text);
        request.setPath("/text");
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().editTerm(glossaryId, termId, singletonList(request));
        assertEquals(termResponseObject.getData().getId(), termId);
    }
}
