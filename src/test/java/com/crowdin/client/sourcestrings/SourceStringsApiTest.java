package com.crowdin.client.sourcestrings;

import com.crowdin.client.core.http.exceptions.HttpBatchBadRequestException;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcestrings.model.*;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SourceStringsApiTest extends TestClient {

    private final String text = "Not all videos are shown to users. See more";
    private final Long projectId = 3L;
    private final Long id = 2814L;
    private final Long branchId = 667L;
    private final Long storageId = 61L;
    private final Long labelId = 1L;
    private final String uploadId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/uploads/" + uploadId, HttpGet.METHOD_NAME, "api/strings/uploadStrings.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/uploads", HttpPost.METHOD_NAME, "api/strings/uploadStringsReq.json", "api/strings/uploadStrings.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpGet.METHOD_NAME, "api/strings/listStrings.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpGet.METHOD_NAME, "api/strings/listStrings.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPost.METHOD_NAME, "api/strings/addStringRequest.json", "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPost.METHOD_NAME, "api/strings/addPluralStringRequest.json", "api/strings/pluralString.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPost.METHOD_NAME, "api/strings/addStringStringsBasedRequest.json", "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPost.METHOD_NAME, "api/strings/addStringPluralStringsBasedRequest.json", "api/strings/pluralString.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpGet.METHOD_NAME, "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpPatch.METHOD_NAME, "api/strings/editString.json", "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPatch.METHOD_NAME, "api/strings/stringBatchOperationsRequest.json", "api/strings/listStrings.json")
        );
    }

    @Test
    public void uploadStringsStatusTest() {
        ResponseObject<UploadStringsProgress> uploadStringsProgressResponseObject = this.getSourceStringsApi().uploadStringsStatus(projectId, uploadId);
        assertEquals(uploadStringsProgressResponseObject.getData().getIdentifier(), uploadId);
        assertEquals(uploadStringsProgressResponseObject.getData().getAttributes().getBranchId(), branchId);
        assertEquals(uploadStringsProgressResponseObject.getData().getAttributes().getStorageId(), storageId);
        assertEquals(uploadStringsProgressResponseObject.getData().getAttributes().getLabelIds().get(0), labelId);
    }

    @Test
    public void uploadStringsTest() {
        UploadStringsRequest request = new UploadStringsRequest();
        request.setBranchId(branchId);
        request.setStorageId(storageId);
        request.setLabelIds(singletonList(labelId));
        ResponseObject<UploadStringsProgress> uploadStringsProgressResponseObject = this.getSourceStringsApi().uploadStrings(projectId, request);
        assertEquals(uploadStringsProgressResponseObject.getData().getIdentifier(), uploadId);
        assertEquals(uploadStringsProgressResponseObject.getData().getAttributes().getBranchId(), branchId);
        assertEquals(uploadStringsProgressResponseObject.getData().getAttributes().getLabelIds().get(0), labelId);
    }

    @Test
    public void listStringsTest() {
        ResponseList<SourceString> sourceStringResponseList = this.getSourceStringsApi().listSourceStrings(projectId, ListSourceStringsParams.builder().build());
        assertEquals(sourceStringResponseList.getData().size(), 1);
        assertEquals(sourceStringResponseList.getData().get(0).getData().getId(), id);
        assertEquals(sourceStringResponseList.getData().get(0).getData().getText(), text);
        assertEquals(sourceStringResponseList.getData().get(0).getData().getBranchId(), branchId);
        assertNull(sourceStringResponseList.getData().get(0).getData().getMasterStringId());
        assertFalse(sourceStringResponseList.getData().get(0).getData().isDuplicate());
    }

    @Test
    public void addStringTest() {
        AddSourceStringRequest request = new AddSourceStringRequest();
        request.setText(text);
        request.setIdentifier("6a1821e6499ebae94de4b880fd93b985");
        request.setFileId(id);
        request.setContext("shown on main page");
        request.setIsHidden(false);
        request.setMaxLength(35);
        request.setLabelIds(Arrays.asList(1L));
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().addSourceString(projectId, request);
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), text);
    }

    @Test
    public void addPluralStringTest() {
        AddSourcePluralStringRequest request = new AddSourcePluralStringRequest();
        PluralText text = new PluralText();
        text.setOne("string");
        text.setOther("strings");
        request.setText(text);
        request.setIdentifier("6a1821e6499ebae94de4b880fd93b985");
        request.setFileId(id);
        request.setContext("shown on main page");
        request.setIsHidden(false);
        request.setMaxLength(35);
        request.setLabelIds(Arrays.asList(1L));
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().addSourcePluralString(projectId, request);
        Map<String, String> expected = new HashMap<String, String>() {{
            put("one", "string");
            put("other", "strings");
        }};
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), expected);
    }

    @Test
    public void addStringStringsBasedTest() {
        AddSourceStringStringsBasedRequest request = new AddSourceStringStringsBasedRequest();
        request.setText(text);
        request.setIdentifier("6a1821e6499ebae94de4b880fd93b985");
        request.setBranchId(branchId);
        request.setContext("shown on main page");
        request.setIsHidden(false);
        request.setMaxLength(35);
        request.setLabelIds(Arrays.asList(1L));
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().addSourceStringStringsBased(projectId, request);
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), text);
    }

    @Test
    public void addPluralStringStringsBasedTest() {
        AddSourcePluralStringStringsBasedRequest request = new AddSourcePluralStringStringsBasedRequest();
        PluralText text = new PluralText();
        text.setOne("string");
        text.setOther("strings");
        request.setText(text);
        request.setIdentifier("6a1821e6499ebae94de4b880fd93b985");
        request.setBranchId(branchId);
        request.setContext("shown on main page");
        request.setIsHidden(false);
        request.setMaxLength(35);
        request.setLabelIds(Arrays.asList(1L));
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().addSourcePluralStringStringsBased(projectId, request);
        Map<String, String> expected = new HashMap<String, String>() {{
            put("one", "string");
            put("other", "strings");
        }};
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), expected);
    }

    @Test
    public void getStringTest() {
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().getSourceString(projectId, id);
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), text);
        assertEquals(sourceStringResponseObject.getData().getBranchId(), branchId);
    }

    @Test
    public void deleteStringTest() {
        this.getSourceStringsApi().deleteSourceString(projectId, id);
    }

    @Test
    public void editStringTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(text);
        request.setPath("/text");
        ResponseObject<SourceString> sourceStringResponseObject = this.getSourceStringsApi().editSourceString(projectId, id, singletonList(request));
        assertEquals(sourceStringResponseObject.getData().getId(), id);
        assertEquals(sourceStringResponseObject.getData().getText(), text);
    }

    @Test
    public void stringBatchOperationsTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/2814/isHidden");
                setValue(true);
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/2814/context");
                setValue("some context");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.ADD);
                setPath("/-");
                setValue(new SourceStringForm() {{
                    setText("new added string");
                    setIdentifier("a.b.c");
                    setContext("context for new string");
                    setFileId(5L);
                    setIsHidden(false);
                }});
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REMOVE);
                setPath("/2815");
            }});
        }};

        ResponseList<SourceString> response = this.getSourceStringsApi().stringBatchOperations(projectId, request);

        SourceString item = response.getData().get(0).getData();
        assertNotNull(item);

        assertEquals(2814, item.getId());
        assertEquals(2, item.getProjectId());
        assertEquals(48, item.getFileId());
        assertEquals(667, item.getBranchId());
    }
}
