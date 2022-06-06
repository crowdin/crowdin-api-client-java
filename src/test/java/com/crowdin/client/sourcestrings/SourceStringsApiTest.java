package com.crowdin.client.sourcestrings;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcestrings.model.AddSourceStringRequest;
import com.crowdin.client.sourcestrings.model.SourceString;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SourceStringsApiTest extends TestClient {

    private final String text = "Not all videos are shown to users. See more";
    private final Long projectId = 3L;
    private final Long id = 2814L;
    private final Long branchId = 667L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpGet.METHOD_NAME, "api/strings/listStrings.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings", HttpPost.METHOD_NAME, "api/strings/addStringRequest.json", "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpGet.METHOD_NAME, "api/strings/string.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings/" + id, HttpPatch.METHOD_NAME, "api/strings/editString.json", "api/strings/string.json")
        );
    }

    @Test
    public void listStringsTest() {
        ResponseList<SourceString> sourceStringResponseList = this.getSourceStringsApi().listSourceStrings(projectId, null, null, null, null, null, null, null, null, null);
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
}
