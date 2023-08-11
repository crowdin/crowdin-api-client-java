package com.crowdin.client.sourcefiles;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcefiles.model.*;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SourceFilesApiTest extends TestClient {

    private final Long branchId = 34L;
    private final Long directoryId = 4L;
    private final Long projectId = 3L;
    private final Long project2Id = 4L;
    private final Long fileId = 44L;
    private final Long storageId = 61L;
    private final Long fileRevisionId = 2L;
    private final Long buildId = 42L;
    private final String branchName = "develop-master";
    private final String directoryName = "main";
    private final String fileName = "umbrella_app.xliff";
    private final String downloadLink = "test.com";
    private final String status = "finished";
    private final List<Long> attachLabelIds = Arrays.asList(1L);
    private final List<Long> detachLabelIds = attachLabelIds;
    private final TimeZone tz = TimeZone.getTimeZone("GMT");

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/branches", HttpGet.METHOD_NAME, "api/sourcefiles/listBranches.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/branches", HttpPost.METHOD_NAME, "api/sourcefiles/addBranchRequest.json", "api/sourcefiles/branch.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + branchId, HttpGet.METHOD_NAME, "api/sourcefiles/branch.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + branchId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + branchId, HttpPatch.METHOD_NAME, "api/sourcefiles/editBranch.json", "api/sourcefiles/branch.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories", HttpGet.METHOD_NAME, "api/sourcefiles/listDirectories.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories", HttpPost.METHOD_NAME, "api/sourcefiles/addDirectoryRequest.json", "api/sourcefiles/directory.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories/" + directoryId, HttpGet.METHOD_NAME, "api/sourcefiles/directory.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories/" + directoryId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/directories/" + directoryId, HttpPatch.METHOD_NAME, "api/sourcefiles/editDirectory.json", "api/sourcefiles/directory.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files", HttpGet.METHOD_NAME, "api/sourcefiles/listFiles.json"),
                RequestMock.build(String.format("%s/projects/%s/files", this.url, project2Id), HttpGet.METHOD_NAME, "api/sourcefiles/listFileInfos.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files", HttpPost.METHOD_NAME, "api/sourcefiles/addFileRequest.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpGet.METHOD_NAME, "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpPut.METHOD_NAME, "api/sourcefiles/updateOrRestoreFileRequest.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpPatch.METHOD_NAME, "api/sourcefiles/editFile.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/download", HttpGet.METHOD_NAME, "api/sourcefiles/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/preview", HttpGet.METHOD_NAME, "api/sourcefiles/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions", HttpGet.METHOD_NAME, "api/sourcefiles/listFileRevisions.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions/" + fileRevisionId, HttpGet.METHOD_NAME, "api/sourcefiles/fileRevision.json"),
                RequestMock.build(String.format("%s/projects/%d/strings/reviewed-builds", this.url, projectId), HttpGet.METHOD_NAME, "api/sourcefiles/listReviewedSourceFileBuilds.json"),
                RequestMock.build(String.format("%s/projects/%d/strings/reviewed-builds", url, projectId), HttpPost.METHOD_NAME, "api/sourcefiles/buildReviewedSourceFilesRequest.json", "api/sourcefiles/buildReviewedSourceFiles.json"),
                RequestMock.build(String.format("%s/projects/%d/strings/reviewed-builds/%d", url, projectId, buildId), HttpGet.METHOD_NAME, "api/sourcefiles/checkReviewedSourceFilesBuildStatus.json"),
                RequestMock.build(String.format("%s/projects/%d/strings/reviewed-builds/%d/download", url, projectId, buildId), HttpGet.METHOD_NAME, "api/sourcefiles/downloadReviewedSourceFiles.json")
        );
    }

    @Test
    public void listBranchesTest() {
        ResponseList<Branch> branchResponseList = this.getSourceFilesApi().listBranches(projectId, null, null, null);
        assertEquals(branchResponseList.getData().size(), 1);
        assertEquals(branchResponseList.getData().get(0).getData().getId(), branchId);
        assertEquals(branchResponseList.getData().get(0).getData().getName(), branchName);
    }

    @Test
    public void addBranchTest() {
        AddBranchRequest request = new AddBranchRequest();
        request.setName(branchName);
        ResponseObject<Branch> branchResponseObject = this.getSourceFilesApi().addBranch(projectId, request);
        assertEquals(branchResponseObject.getData().getId(), branchId);
        assertEquals(branchResponseObject.getData().getName(), branchName);
    }

    @Test
    public void getBranchTest() {
        ResponseObject<Branch> branchResponseObject = this.getSourceFilesApi().getBranch(projectId, branchId);
        assertEquals(branchResponseObject.getData().getId(), branchId);
        assertEquals(branchResponseObject.getData().getName(), branchName);
    }

    @Test
    public void deleteBranchTest() {
        this.getSourceFilesApi().deleteBranch(projectId, branchId);
    }

    @Test
    public void editBranchTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(branchName);
        request.setPath("/name");
        ResponseObject<Branch> branchResponseObject = this.getSourceFilesApi().editBranch(projectId, branchId, Arrays.asList(request));
        assertEquals(branchResponseObject.getData().getId(), branchId);
        assertEquals(branchResponseObject.getData().getName(), branchName);
    }

    @Test
    public void listDirectoriesTest() {
        ResponseList<Directory> directoryResponseList = this.getSourceFilesApi().listDirectories(projectId, null, null, null, null, null, null);
        assertEquals(directoryResponseList.getData().size(), 1);
        assertEquals(directoryResponseList.getData().get(0).getData().getId(), directoryId);
        assertEquals(directoryResponseList.getData().get(0).getData().getName(), directoryName);
    }

    @Test
    public void addDirectoryTest() {
        AddDirectoryRequest request = new AddDirectoryRequest();
        request.setName(directoryName);
        ResponseObject<Directory> directoryResponseObject = this.getSourceFilesApi().addDirectory(projectId, request);
        assertEquals(directoryResponseObject.getData().getId(), directoryId);
        assertEquals(directoryResponseObject.getData().getName(), directoryName);
    }

    @Test
    public void getDirectoryTest() {
        TimeZone.setDefault(tz);
        ResponseObject<Directory> directoryResponseObject = this.getSourceFilesApi().getDirectory(projectId, directoryId);
        assertEquals(directoryResponseObject.getData().getId(), directoryId);
        assertEquals(directoryResponseObject.getData().getName(), directoryName);
        assertEquals(new Date(119,Calendar.SEPTEMBER,19,14,14,0),directoryResponseObject.getData().getCreatedAt());
    }

    @Test
    public void deleteDirectoryTest() {
        this.getSourceFilesApi().deleteDirectory(projectId, directoryId);
    }

    @Test
    public void editDirectoryTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(directoryName);
        request.setPath("/name");
        ResponseObject<Directory> directoryResponseObject = this.getSourceFilesApi().editDirectory(projectId, directoryId, Arrays.asList(request));
        assertEquals(directoryResponseObject.getData().getId(), directoryId);
        assertEquals(directoryResponseObject.getData().getName(), directoryName);
    }

    @Test
    public void listFilesTest() {
        ResponseList<File> fileResponseList = (ResponseList<File>) this.getSourceFilesApi().listFiles(projectId, null, null, null, null, null, null);
        assertEquals(fileResponseList.getData().size(), 5);
        assertEquals(fileResponseList.getData().get(0).getData().getId(), fileId);
        assertEquals(fileResponseList.getData().get(0).getData().getName(), fileName);
        ExportOptions exportOptions = fileResponseList.getData().get(0).getData().getExportOptions();
        assertTrue(exportOptions instanceof GeneralFileExportOptions);
        assertEquals(((GeneralFileExportOptions) exportOptions).getExportPattern(), "/localization/%locale%/%file_name%.%file_extension%");

        assertEquals(fileResponseList.getData().get(1).getData().getId(), Long.valueOf(45L));
        assertEquals(fileResponseList.getData().get(1).getData().getName(), "fileA.properties");
        exportOptions = fileResponseList.getData().get(1).getData().getExportOptions();
        assertTrue(exportOptions instanceof PropertyFileExportOptions);
        assertEquals(((PropertyFileExportOptions) exportOptions).getExportPattern(), "/files/fileA.properties");
        assertEquals(((PropertyFileExportOptions) exportOptions).getEscapeQuotes(), Integer.valueOf(3));
        assertEquals(((PropertyFileExportOptions) exportOptions).getEscapeSpecialCharacters(), null);

        assertEquals(fileResponseList.getData().get(2).getData().getId(), Long.valueOf(46L));
        assertEquals(fileResponseList.getData().get(2).getData().getName(), "fileB.properties");
        exportOptions = fileResponseList.getData().get(2).getData().getExportOptions();
        assertTrue(exportOptions instanceof PropertyFileExportOptions);
        assertEquals(((PropertyFileExportOptions) exportOptions).getExportPattern(), "/files/fileB.properties");
        assertEquals(((PropertyFileExportOptions) exportOptions).getEscapeQuotes(), null);
        assertEquals(((PropertyFileExportOptions) exportOptions).getEscapeSpecialCharacters(), Integer.valueOf(1));

        assertEquals(fileResponseList.getData().get(3).getData().getId(), Long.valueOf(47L));
        assertEquals(fileResponseList.getData().get(3).getData().getName(), "guide.odt");
        assertEquals(fileResponseList.getData().get(3).getData().getType(), "docx");
        ImportOptions importOptions = fileResponseList.getData().get(3).getData().getImportOptions();
        assertTrue(importOptions instanceof DocxFileImportOptions);
        assertEquals(((DocxFileImportOptions) importOptions).getCleanTagsAggressively(), false);
        assertEquals(((DocxFileImportOptions) importOptions).getTranslateHiddenText(), true);
        assertEquals(((DocxFileImportOptions) importOptions).getTranslateHyperlinkUrls(), false);
        assertEquals(((DocxFileImportOptions) importOptions).getTranslateHiddenRowsAndColumns(), false);
        assertEquals(((DocxFileImportOptions) importOptions).getImportNotes(), true);
        assertEquals(((DocxFileImportOptions) importOptions).getImportHiddenSlides(), false);
        assertEquals(((DocxFileImportOptions) importOptions).getContentSegmentation(), true);
        assertEquals(((DocxFileImportOptions) importOptions).getSrxStorageId(), null);

        assertEquals(fileResponseList.getData().get(4).getData().getId(), Long.valueOf(48L));
        assertEquals(fileResponseList.getData().get(4).getData().getName(), "fileB.js");
        exportOptions = fileResponseList.getData().get(4).getData().getExportOptions();
        assertTrue(exportOptions instanceof JavaScriptFileExportOptions);
        assertEquals(((JavaScriptFileExportOptions) exportOptions).getExportPattern(), "/files/fileB.js");
        assertEquals(((JavaScriptFileExportOptions) exportOptions).getExportQuotes().name().toLowerCase(), "single");
    }

    @Test
    public void listFileInfosTest() {
        ResponseList<? extends FileInfo> response = this.getSourceFilesApi().listFiles(project2Id, null, null, null, null, null, null);
        assertEquals(response.getData().size(), 1);
        assertTrue(response.getData().get(0).getData() instanceof FileInfo);
    }

    @Test
    public void addFileTest() {
        AddFileRequest request = new AddFileRequest();
        request.setName(fileName);
        request.setStorageId(storageId);
        request.setAttachLabelIds(attachLabelIds);
        request.setExcludedTargetLanguages(Arrays.asList("en", "es", "pl"));
        ResponseObject<File> fileResponseObject = (ResponseObject<File>) this.getSourceFilesApi().addFile(projectId, request);
        assertEquals(fileResponseObject.getData().getId(), fileId);
        assertEquals(fileResponseObject.getData().getName(), fileName);
    }

    @Test
    public void getFileTest() {
        ResponseObject<File> fileResponseObject = (ResponseObject<File>) this.getSourceFilesApi().getFile(projectId, fileId);
        assertEquals(fileResponseObject.getData().getId(), fileId);
        assertEquals(fileResponseObject.getData().getName(), fileName);
    }

    @Test
    public void updateOrRestoreFileTest() {
        UpdateFileRequest request = new UpdateFileRequest();
        request.setStorageId(storageId);
        request.setUpdateOption(UpdateOption.CLEAR_TRANSLATIONS_AND_APPROVALS);
        PropertyFileExportOptions exportOptions = new PropertyFileExportOptions();
        exportOptions.setEscapeQuotes(3);
        request.setExportOptions(exportOptions);
        SpreadsheetFileImportOptions importOptions = new SpreadsheetFileImportOptions();
        importOptions.setFirstLineContainsHeader(true);
        importOptions.setImportTranslations(false);
        Map<String, Integer> scheme = new HashMap<>();
        scheme.put("identifier", 0);
        scheme.put("sourcePhrase", 1);
        scheme.put("en", 2);
        scheme.put("de", 3);
        importOptions.setScheme(scheme);
        request.setImportOptions(importOptions);
        request.setAttachLabelIds(attachLabelIds);
        request.setDetachLabelIds(detachLabelIds);
        ResponseObject<File> fileResponseObject = (ResponseObject<File>) this.getSourceFilesApi().updateOrRestoreFile(projectId, fileId, request);
        assertEquals(fileResponseObject.getData().getId(), fileId);
        assertEquals(fileResponseObject.getData().getName(), fileName);
    }

    @Test
    public void deleteFileTest() {
        this.getSourceFilesApi().deleteFile(projectId, fileId);
    }

    @Test
    public void editFileTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(fileName);
        request.setPath("/name");
        ResponseObject<File> fileResponseObject = (ResponseObject<File>) this.getSourceFilesApi().editFile(projectId, fileId, Arrays.asList(request));
        assertEquals(fileResponseObject.getData().getId(), fileId);
        assertEquals(fileResponseObject.getData().getName(), fileName);
    }

    @Test
    public void downloadFileTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getSourceFilesApi().downloadFile(projectId, fileId);
        assertEquals(downloadLink, downloadLinkResponseObject.getData().getUrl());
    }

    @Test
    public void listFileRevisionsTest() {
        ResponseList<FileRevision> fileRevisionResponseList = this.getSourceFilesApi().listFileRevisions(projectId, fileId, null, null);
        assertEquals(fileRevisionResponseList.getData().size(), 1);
        assertEquals(fileRevisionResponseList.getData().get(0).getData().getId(), fileRevisionId);
    }

    @Test
    public void getFileRevisionTest() {
        ResponseObject<FileRevision> fileRevision = this.getSourceFilesApi().getFileRevision(projectId, fileId, fileRevisionId);
        assertEquals(fileRevision.getData().getId(), fileRevisionId);
    }

    @Test
    public void listReviewedSourceFileBuildsTest() {
        ResponseList<ReviewedStringsBuild> response = this.getSourceFilesApi().listReviewedSourceFilesBuilds(projectId, null, null, null);
        assertEquals(response.getData().size(), 1);
        assertEquals(buildId, response.getData().get(0).getData().getId());
    }

    @Test
    public void buildReviewedSourceFilesTest() {
        BuildReviewedSourceFilesRequest request = new BuildReviewedSourceFilesRequest();
        request.setBranchId(branchId);
        ResponseObject<ReviewedStringsBuild> response = this.getSourceFilesApi().buildReviewedSourceFiles(projectId, request);
        assertEquals(buildId, response.getData().getId());
    }

    @Test
    public void checkReviewedSourceFileBuildStatusTest() {
        ResponseObject<ReviewedStringsBuild> response = this.getSourceFilesApi().checkReviewedSourceFilesBuildStatus(projectId, buildId);
        assertEquals(buildId, response.getData().getId());
    }

    @Test
    public void downloadReviewedSourceFilesTest() {
        ResponseObject<DownloadLink> response = this.getSourceFilesApi().downloadReviewedSourceFiles(projectId, buildId);
        assertEquals(downloadLink, response.getData().getUrl());
    }

    @Test
    public void downloadFilePreview() {
        ResponseObject<DownloadLink> response = this.getSourceFilesApi().downloadFilePreview(projectId, fileId);
        assertEquals(downloadLink, response.getData().getUrl());
    }
}
