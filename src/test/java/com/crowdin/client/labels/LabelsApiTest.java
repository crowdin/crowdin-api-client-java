package com.crowdin.client.labels;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.labels.model.AddLabelRequest;
import com.crowdin.client.labels.model.Label;
import com.crowdin.client.labels.model.LabelToStringsRequest;
import com.crowdin.client.sourcestrings.model.SourceString;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LabelsApiTest extends TestClient {

    private final String apiFiles = "api/labels/";

    private String file(String fileName) {
        return apiFiles + fileName;
    }

    private final Long projectId = 8L;
    private final String labelTitle = "main";
    private final Long labelId = 34L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format("%s/projects/%d/labels", this.url, projectId), HttpGet.METHOD_NAME,
                file("listLabels.json")),
            RequestMock.build(String.format("%s/projects/%d/labels", this.url, projectId), HttpPost.METHOD_NAME,
                file("addLabelRequest.json"), file("label.json")),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpGet.METHOD_NAME,
                file("label.json")),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpPatch.METHOD_NAME,
                file("editLabelRequest.json"), file("label.json")),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId), HttpPost.METHOD_NAME,
                file("labelToStringsRequest.json"), file("listStrings.json")),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId), HttpDelete.METHOD_NAME,
                file("listStrings.json"))
        );
    }

    @Test
    public void listLabelsTest() {
        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(projectId, null, null);
        assertEquals(1, labelResponseList.getData().size());
        assertEquals(labelTitle, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void addLabelTest() {
        AddLabelRequest request = new AddLabelRequest() {{
                setTitle(labelTitle);
            }};
        ResponseObject<Label> labelResponseObject = this.getLabelsApi().addLabel(projectId, request);
        assertNotNull(labelResponseObject.getData());
        assertEquals(labelTitle, labelResponseObject.getData().getTitle());
    }

    @Test
    public void getLabelTest() {
        ResponseObject<Label> labelResponseObject = this.getLabelsApi().getLabel(projectId, labelId);
        assertNotNull(labelResponseObject.getData());
        assertEquals(labelId, labelResponseObject.getData().getId());
    }

    @Test
    public void deleteLabelTest() {
        this.getLabelsApi().deleteLabel(projectId, labelId);
    }

    @Test
    public void editLabelTest() {
        List<PatchRequest> patches = Arrays.asList(
            new PatchRequest() {{
                    setOp(PatchOperation.REPLACE);
                    setPath("/title");
                }}
        );
        ResponseObject<Label> response = this.getLabelsApi().editLabel(projectId, labelId, patches);
        assertNotNull(response.getData());
        assertEquals(labelId, response.getData().getId());
    }

    @Test
    public void assignLabelToStringTest() {
        LabelToStringsRequest request = new LabelToStringsRequest() {{
                setStringIds(Arrays.asList(1L));
            }};
        ResponseList<SourceString> response = this.getLabelsApi().assignLabelToStrings(projectId, labelId, request);
        assertEquals(1, response.getData().size());
    }

    @Test
    public void unassignLabelToStringTest() {
        ResponseList<SourceString> response = this.getLabelsApi().unassignLabelToStrings(projectId, labelId);
        assertEquals(1, response.getData().size());
    }


}
