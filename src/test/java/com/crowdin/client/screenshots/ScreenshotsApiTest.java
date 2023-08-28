package com.crowdin.client.screenshots;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.screenshots.model.AddScreenshotRequest;
import com.crowdin.client.screenshots.model.AddTagRequest;
import com.crowdin.client.screenshots.model.AutoTagReplaceTagsRequest;
import com.crowdin.client.screenshots.model.Position;
import com.crowdin.client.screenshots.model.Screenshot;
import com.crowdin.client.screenshots.model.Tag;
import com.crowdin.client.screenshots.model.UpdateScreenshotRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreenshotsApiTest extends TestClient {

    private final Long projectId = 3L;
    private final Long screenshotId = 2L;
    private final Long storageId = 71L;
    private final Long fileId = 87L;
    private final Long branchId = 88L;
    private final Long directoryId = 89L;
    private final Long tagId = 98L;
    private final Long stringId = 12L;
    private final String name = "translate_with_siri.jpg";
    private final TimeZone tz = TimeZone.getTimeZone("GMT");

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots", HttpGet.METHOD_NAME, "api/screenshots/listScreenshots.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots", HttpPost.METHOD_NAME, "api/screenshots/addScreenshotRequest.json", "api/screenshots/screenshot.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, HttpGet.METHOD_NAME, "api/screenshots/screenshot.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, HttpPut.METHOD_NAME, "api/screenshots/updateScreenshotRequest.json", "api/screenshots/screenshot.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, HttpPatch.METHOD_NAME, "api/screenshots/editScreenshot.json", "api/screenshots/screenshot.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", HttpGet.METHOD_NAME, "api/screenshots/listTags.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", HttpPut.METHOD_NAME, "api/screenshots/replaceTag.json", (String) null),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", HttpPost.METHOD_NAME, "api/screenshots/addTagRequest.json", "api/screenshots/tags.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, HttpGet.METHOD_NAME, "api/screenshots/tag.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, HttpPatch.METHOD_NAME, "api/screenshots/editTag.json", "api/screenshots/tag.json")
        );
    }

    @Test
    public void listScreenshotsTest() {
        ResponseList<Screenshot> screenshotResponseList = this.getScreenshotsApi().listScreenshots(projectId, null, null, null);
        assertEquals(screenshotResponseList.getData().size(), 1);
        assertEquals(screenshotResponseList.getData().get(0).getData().getId(), screenshotId);
    }

    @Test
    public void addScreenshotTest() {
        AddScreenshotRequest request = new AddScreenshotRequest();
        request.setName(name);
        request.setStorageId(storageId);
        request.setFileId(fileId);
        request.setBranchId(branchId);
        request.setDirectoryId(directoryId);
        ResponseObject<Screenshot> screenshotResponseObject = this.getScreenshotsApi().addScreenshot(projectId, request);
        assertEquals(screenshotResponseObject.getData().getId(), screenshotId);
    }

    @Test
    public void getScreenshotTest() {
        ResponseObject<Screenshot> screenshot = this.getScreenshotsApi().getScreenshot(projectId, screenshotId);
        assertEquals(screenshot.getData().getId(), screenshotId);
    }

    @Test
    public void updateScreenshotTest() {
        TimeZone.setDefault(tz);
        UpdateScreenshotRequest request = new UpdateScreenshotRequest();
        request.setName(name);
        request.setStorageId(storageId);
        ResponseObject<Screenshot> screenshotResponseObject = this.getScreenshotsApi().updateScreenshot(projectId, screenshotId, request);
        assertEquals(screenshotResponseObject.getData().getId(), screenshotId);
        assertEquals(new Date(119,Calendar.SEPTEMBER,23,9,35,31), screenshotResponseObject.getData().getTags().get(0).getCreatedAt());
        assertEquals(new Date(119,Calendar.SEPTEMBER,23,9,29,19), screenshotResponseObject.getData().getUpdatedAt());
    }

    @Test
    public void deleteScreenshotTest() {
        this.getScreenshotsApi().deleteScreenshot(projectId, screenshotId);
    }

    @Test
    public void editScreenshotTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Screenshot> screenshotResponseObject = this.getScreenshotsApi().editScreenshot(projectId, screenshotId, singletonList(request));
        assertEquals(screenshotResponseObject.getData().getId(), screenshotId);
    }

    @Test
    public void listTagsTest() {
        ResponseList<Tag> tagResponseList = this.getScreenshotsApi().listTags(projectId, screenshotId, null, null);
        assertEquals(tagResponseList.getData().size(), 1);
        assertEquals(tagResponseList.getData().get(0).getData().getId(), tagId);
    }

    @Test
    public void replaceTagsTest() {
        AutoTagReplaceTagsRequest request = new AutoTagReplaceTagsRequest();
        request.setAutoTag(true);
        this.getScreenshotsApi().replaceTags(projectId, screenshotId, request);
    }

    @Test
    public void addTagsTest() {
        AddTagRequest request = new AddTagRequest();
        request.setStringId(stringId);
        Position position = new Position();
        position.setX(1);
        position.setY(2);
        position.setWidth(100);
        position.setHeight(200);
        request.setPosition(position);
        ResponseObject<List<Tag>> tagResponseObject = this.getScreenshotsApi().addTag(projectId, screenshotId, singletonList(request));
        assertEquals(tagResponseObject.getData().get(0).getId(), tagId);
    }

    @Test
    public void clearTagsTest() {
        this.getScreenshotsApi().clearTags(projectId, screenshotId);
    }

    @Test
    public void getTagTest() {
        ResponseObject<Tag> tagResponseObject = this.getScreenshotsApi().getTag(projectId, screenshotId, tagId);
        assertEquals(tagResponseObject.getData().getId(), tagId);
    }

    @Test
    public void deleteTagTest() {
        this.getScreenshotsApi().deleteTag(projectId, screenshotId, tagId);
    }

    @Test
    public void editTagTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(stringId);
        request.setPath("/stringId");
        ResponseObject<Tag> tagResponseObject = this.getScreenshotsApi().editTag(projectId, screenshotId, tagId, singletonList(request));
        assertEquals(tagResponseObject.getData().getId(), tagId);
    }
}
