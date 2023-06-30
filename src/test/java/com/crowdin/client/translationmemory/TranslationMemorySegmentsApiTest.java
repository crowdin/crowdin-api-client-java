package com.crowdin.client.translationmemory;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translationmemory.model.*;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TranslationMemorySegmentsApiTest extends TestClient {
    private final Long tmId = 1L;
    private final Long segmentId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        formUrl_tmSegments(tmId),
                        HttpGet.METHOD_NAME,
                        "api/translationmemory/segments/listTmSegmentsResponse.json",
                        new HashMap<String, Integer>() {{
                            put("limit", 20);
                            put("offset", 10);
                        }}
                ),

                // CREATE
                RequestMock.build(
                        formUrl_tmSegments(tmId),
                        HttpPost.METHOD_NAME,
                        "api/translationmemory/segments/createTmSegmentRequest.json",
                        "api/translationmemory/segments/commonResponses_single.json"
                ),

                // GET
                RequestMock.build(
                        formUrl_tmSegmentId(tmId, segmentId),
                        HttpGet.METHOD_NAME,
                        "api/translationmemory/segments/commonResponses_single.json"
                ),

                // DELETE
                RequestMock.build(
                        formUrl_tmSegmentId(tmId, segmentId),
                        HttpDelete.METHOD_NAME
                ),

                // EDIT
                RequestMock.build(
                        formUrl_tmSegmentId(tmId, segmentId),
                        HttpPatch.METHOD_NAME,
                        "api/translationmemory/segments/editTmSegmentRequest.json",
                        "api/translationmemory/segments/commonResponses_single.json"
                )
        );
    }

    //<editor-fold desc="Form Url methods for mocks">

    private String formUrl_tmSegments(Long tmId) {
        return this.url + "/tms/" + tmId + "/segments";
    }

    private String formUrl_tmSegmentId(Long tmId, Long segmentId) {
        return this.url + "/tms/" + tmId + "/segments/" + segmentId;
    }

    //</editor-fold>

    @Test
    public void listTmSegments() {
        ResponseList<TmSegment> response = this.getTranslationMemoryApi().listTmSegments(tmId, 20, 10);
        assertTmSegment(response.getData().get(0).getData());
    }

    @Test
    public void createTmSegment() {
        CreateTmSegmentRequest request = new CreateTmSegmentRequest() {{
           setRecords(Collections.singletonList(new TmSegmentRecordForm() {{
               setLanguageId("uk");
               setText("Перекладений текст");
           }}));
        }};

        ResponseObject<TmSegment> response = this.getTranslationMemoryApi().createTmSegment(tmId, request);
        assertTmSegment(response.getData());
    }

    @Test
    public void getTmSegment() {
        ResponseObject<TmSegment> response = this.getTranslationMemoryApi().getTmSegment(tmId, segmentId);
        assertTmSegment(response.getData());
    }

    @Test
    public void deleteTmSegment() {
        this.getTranslationMemoryApi().deleteTmSegment(tmId, segmentId);
    }

    @Test
    public void editTmSegment() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
           add(new PatchRequest() {{
               setOp(PatchOperation.ADD);
               setPath("/records/-");
               setValue(new TmSegmentRecordOperationAdd() {{
                   setText("Перекладений текст");
                   setLanguageId("uk");
               }});
           }});
           add(new PatchRequest() {{
               setOp(PatchOperation.REPLACE);
               setPath("/records/1/text");
               setValue("Перекладений текст 2");
           }});
           add(new PatchRequest() {{
               setOp(PatchOperation.REMOVE);
               setPath("/records/2");
           }});
        }};

        ResponseObject<TmSegment> response = this.getTranslationMemoryApi().editTmSegment(tmId, segmentId, request);
        assertTmSegment(response.getData());
    }

    @SneakyThrows
    private static void assertTmSegment(TmSegment tmSegment) {
        assertNotNull(tmSegment);

        assertNotNull(tmSegment.getRecords());
        assertEquals(1, tmSegment.getRecords().size());

        TmSegmentRecord record = tmSegment.getRecords().get(0);
        assertNotNull(record);

        assertEquals(1, record.getId());
        assertEquals("uk", record.getLanguageId());
        assertEquals("Перекладений текст", record.getText());
        assertEquals(13, record.getUsageCount());
        assertEquals(1, record.getCreatedBy());
        assertEquals(1, record.getUpdatedBy());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        assertEquals(sdf.parse("2019-09-16T13:48:04+00:00"), record.getCreatedAt());
        assertEquals(sdf.parse("2019-09-16T13:48:04+00:00"), record.getUpdatedAt());
    }
}
