package com.crowdin.client.translationmemory;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.Format;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translationmemory.model.AddTranslationMemoryRequest;
import com.crowdin.client.translationmemory.model.TranslationMemory;
import com.crowdin.client.translationmemory.model.TranslationMemoryExportRequest;
import com.crowdin.client.translationmemory.model.TranslationMemoryExportStatus;
import com.crowdin.client.translationmemory.model.TranslationMemoryImportRequest;
import com.crowdin.client.translationmemory.model.TranslationMemoryImportStatus;
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

public class TranslationMemoryApiTest extends TestClient {

    private final Long tmId = 4L;
    private final String name = "Knowledge Base's TM";
    private final String exportId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String importId = "b5215a34-1305-4b21-8054-fc2eb252842f";
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/tms", HttpGet.METHOD_NAME, "api/translationmemory/listTms.json"),
                RequestMock.build(this.url + "/tms", HttpPost.METHOD_NAME, "api/translationmemory/addTmRequest.json", "api/translationmemory/tm.json"),
                RequestMock.build(this.url + "/tms/" + tmId, HttpGet.METHOD_NAME, "api/translationmemory/tm.json"),
                RequestMock.build(this.url + "/tms/" + tmId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/tms/" + tmId, HttpPatch.METHOD_NAME, "api/translationmemory/editTm.json", "api/translationmemory/tm.json"),
                RequestMock.build(this.url + "/tms/" + tmId + "/exports", HttpPost.METHOD_NAME, "api/translationmemory/exportTm.json", "api/translationmemory/tmExportStatus.json"),
                RequestMock.build(this.url + "/tms/" + tmId + "/exports/" + exportId, HttpGet.METHOD_NAME, "api/translationmemory/tmExportStatus.json"),
                RequestMock.build(this.url + "/tms/" + tmId + "/exports/" + exportId + "/download", HttpGet.METHOD_NAME, "api/translationmemory/downloadLink.json"),
                RequestMock.build(this.url + "/tms/" + tmId + "/imports", HttpPost.METHOD_NAME, "api/translationmemory/importTm.json", "api/translationmemory/tmImportStatus.json"),
                RequestMock.build(this.url + "/tms/" + tmId + "/imports/" + importId, HttpGet.METHOD_NAME, "api/translationmemory/tmImportStatus.json")
        );
    }

    @Test
    public void listTmsTest() {
        ResponseList<TranslationMemory> translationMemoryResponseList = this.getTranslationMemoryApi().listTms(null, null, null);
        assertEquals(translationMemoryResponseList.getData().size(), 1);
        assertEquals(translationMemoryResponseList.getData().get(0).getData().getId(), tmId);
        assertEquals(translationMemoryResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addTmTest() {
        AddTranslationMemoryRequest request = new AddTranslationMemoryRequest();
        request.setName(name);
        ResponseObject<TranslationMemory> translationMemoryResponseObject = this.getTranslationMemoryApi().addTm(request);
        assertEquals(translationMemoryResponseObject.getData().getId(), tmId);
        assertEquals(translationMemoryResponseObject.getData().getName(), name);
    }

    @Test
    public void getTmTest() {
        ResponseObject<TranslationMemory> translationMemoryResponseObject = this.getTranslationMemoryApi().getTm(tmId);
        assertEquals(translationMemoryResponseObject.getData().getId(), tmId);
        assertEquals(translationMemoryResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteTmTest() {
        this.getTranslationMemoryApi().deleteTm(tmId);
    }

    @Test
    public void editTmTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<TranslationMemory> translationMemoryResponseObject = this.getTranslationMemoryApi().editTm(tmId, singletonList(request));
        assertEquals(translationMemoryResponseObject.getData().getId(), tmId);
        assertEquals(translationMemoryResponseObject.getData().getName(), name);
    }

    @Test
    public void exportTmTest() {
        TranslationMemoryExportRequest request = new TranslationMemoryExportRequest();
        request.setFormat(Format.CSV);
        request.setSourceLanguageId("en");
        request.setTargetLanguageId("de");
        ResponseObject<TranslationMemoryExportStatus> translationMemoryExportStatusResponseObject = this.getTranslationMemoryApi().exportTm(tmId, request);
        assertEquals(translationMemoryExportStatusResponseObject.getData().getIdentifier(), exportId);
    }

    @Test
    public void checkTmExportStatusTest() {
        ResponseObject<TranslationMemoryExportStatus> translationMemoryExportStatusResponseObject = this.getTranslationMemoryApi().checkTmExportStatus(tmId, exportId);
        assertEquals(translationMemoryExportStatusResponseObject.getData().getIdentifier(), exportId);
    }

    @Test
    public void downloadTmTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTranslationMemoryApi().downloadTm(tmId, exportId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void importTmTest() {
        TranslationMemoryImportRequest request = new TranslationMemoryImportRequest();
        request.setStorageId(11L);
        request.setScheme(singletonMap("uk", 4));
        ResponseObject<TranslationMemoryImportStatus> translationMemoryImportStatusResponseObject = this.getTranslationMemoryApi().importTm(tmId, request);
        assertEquals(translationMemoryImportStatusResponseObject.getData().getIdentifier(), importId);
    }

    @Test
    public void checkTmImportStatusTest() {
        ResponseObject<TranslationMemoryImportStatus> translationMemoryImportStatusResponseObject = this.getTranslationMemoryApi().checkTmImportStatus(tmId, importId);
        assertEquals(translationMemoryImportStatusResponseObject.getData().getIdentifier(), importId);
    }
}
