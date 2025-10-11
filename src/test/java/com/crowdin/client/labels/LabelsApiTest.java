package com.crowdin.client.labels;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.labels.model.AddLabelRequest;
import com.crowdin.client.labels.model.Label;
import com.crowdin.client.labels.model.LabelToScreenshotsRequest;
import com.crowdin.client.labels.model.LabelToStringsRequest;
import com.crowdin.client.screenshots.model.Screenshot;
import com.crowdin.client.sourcestrings.model.SourceString;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LabelsApiTest extends TestClient {

    private final Long projectId = 8L;
    private final Long project2Id = 9L;
    private final Long project3Id = 10L;
    private final String labelTitle = "main";
    private final String labelTitle2 = "main-#2";
    private final Long labelId = 34L;
    private final Long labelId2 = 35L;

    private final Map<String, Object> unassignLabelToScreenshotsUrlParams = new HashMap<String, Object>() {{
        put("screenshotIds", "1");
    }};
    private final Map<String, Object> unassignLabelToStringTestUrlParams = new HashMap<String, Object>() {{
        put("stringIds", "1");
    }};

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format("%s/projects/%d/labels", this.url, projectId), HttpGet.METHOD_NAME,
                "api/labels/listLabels.json"),

                RequestMock.build(String.format("%s/projects/%d/labels", this.url, project2Id), HttpGet.METHOD_NAME,
                        "api/labels/listLabelsOrderByIdAsc.json",
                        new HashMap<String, String>() {{
                            put("orderBy", "id%20asc");
                        }}),
                RequestMock.build(String.format("%s/projects/%d/labels", this.url, project3Id), HttpGet.METHOD_NAME,
                        "api/labels/listLabelsOrderByIdDesc.json",
                        new HashMap<String, String>() {{
                            put("orderBy", "id%20desc");
                        }}),

            RequestMock.build(String.format("%s/projects/%d/labels", this.url, projectId), HttpPost.METHOD_NAME,
                "api/labels/addLabelRequest.json", "api/labels/label.json"),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpGet.METHOD_NAME,
                "api/labels/label.json"),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId), HttpPatch.METHOD_NAME,
                "api/labels/editLabelRequest.json", "api/labels/label.json"),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId), HttpPost.METHOD_NAME,
                "api/labels/labelToStringsRequest.json", "api/labels/listStrings.json"),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId), HttpDelete.METHOD_NAME,
                "api/labels/listStrings.json", unassignLabelToStringTestUrlParams),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/screenshots", this.url, projectId, labelId), HttpPost.METHOD_NAME,
                "api/labels/labelToScreenshotsRequest.json", "api/labels/listScreenshots.json"),
            RequestMock.build(String.format("%s/projects/%d/labels/%d/screenshots", this.url, projectId, labelId), HttpDelete.METHOD_NAME,
                "api/labels/listScreenshots.json", unassignLabelToScreenshotsUrlParams)
        );
    }

    @Test
    public void listLabelsTest() {
        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(projectId, null, null, null);
        assertEquals(1, labelResponseList.getData().size());
        assertEquals(labelTitle, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listLabelsTest_orderByNull() {
        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(projectId, null, null, null, null);
        assertEquals(1, labelResponseList.getData().size());
        assertEquals(labelTitle, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listLabelsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(project2Id, null, null, null, Collections.singletonList(orderById));
        assertEquals(2, labelResponseList.getData().size());
        assertEquals(labelTitle, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(0).getData().getId());

        assertEquals(labelTitle2, labelResponseList.getData().get(1).getData().getTitle());
        assertEquals(labelId2, labelResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listLabelsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(project2Id, null, null, null, Collections.singletonList(orderById));
        assertEquals(2, labelResponseList.getData().size());
        assertEquals(labelTitle, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(0).getData().getId());

        assertEquals(labelTitle2, labelResponseList.getData().get(1).getData().getTitle());
        assertEquals(labelId2, labelResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listLabelsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<Label> labelResponseList = this.getLabelsApi().listLabels(project3Id, null, null, null, Collections.singletonList(orderById));
        assertEquals(2, labelResponseList.getData().size());

        assertEquals(labelTitle2, labelResponseList.getData().get(0).getData().getTitle());
        assertEquals(labelId2, labelResponseList.getData().get(0).getData().getId());

        assertEquals(labelTitle, labelResponseList.getData().get(1).getData().getTitle());
        assertEquals(labelId, labelResponseList.getData().get(1).getData().getId());
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
    public void unassignLabelFromStringTest() {
        ResponseList<SourceString> response = this.getLabelsApi().unassignLabelFromStrings(projectId, labelId, Arrays.asList(1L));
        assertEquals(1, response.getData().size());
    }

    @Test
    public void assignLabelToScreenshot() {
        LabelToScreenshotsRequest request = new LabelToScreenshotsRequest() {{
            setScreenshotIds(Arrays.asList(1L));
        }};
        ResponseList<Screenshot> response = this.getLabelsApi().assignLabelToScreenshots(projectId, labelId, request);
        assertEquals(1, response.getData().size());
    }

    @Test
    public void unassignLabelToScreenshot() {
        ResponseList<Screenshot> response = this.getLabelsApi().unassignLabelFromScreenshots(projectId, labelId, Arrays.asList(1L));
        assertEquals(1, response.getData().size());
    }
}
