package com.crowdin.client.sourcefiles;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcefiles.model.AddBranchRequest;
import com.crowdin.client.sourcefiles.model.AddDirectoryRequest;
import com.crowdin.client.sourcefiles.model.AddFileRequest;
import com.crowdin.client.sourcefiles.model.Branch;
import com.crowdin.client.sourcefiles.model.Directory;
import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileRevision;
import com.crowdin.client.sourcefiles.model.GeneralFileExportOptions;
import com.crowdin.client.sourcefiles.model.PropertyFileExportOptions;
import com.crowdin.client.sourcefiles.model.SpreadsheetFileImportOptions;
import com.crowdin.client.sourcefiles.model.UpdateFileRequest;
import com.crowdin.client.sourcefiles.model.UpdateOption;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SourceFilesApiTest extends TestClient {

    private final Long branchId = 34L;
    private final Long directoryId = 4L;
    private final Long projectId = 3L;
    private final Long fileId = 44L;
    private final Long storageId = 61L;
    private final Long fileRevisionId = 2L;
    private final String branchName = "develop-master";
    private final String directoryName = "main";
    private final String fileName = "umbrella_app.xliff";
    private final String downloadLink = "test.com";

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
                RequestMock.build(this.url + "/projects/" + projectId + "/files", HttpPost.METHOD_NAME, "api/sourcefiles/addFileRequest.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpGet.METHOD_NAME, "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpPut.METHOD_NAME, "api/sourcefiles/updateOrRestoreFileRequest.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId, HttpPatch.METHOD_NAME, "api/sourcefiles/editFile.json", "api/sourcefiles/file.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/download", HttpGet.METHOD_NAME, "api/sourcefiles/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions", HttpGet.METHOD_NAME, "api/sourcefiles/listFileRevisions.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions/" + fileRevisionId, HttpGet.METHOD_NAME, "api/sourcefiles/fileRevision.json")
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
        ResponseList<Directory> directoryResponseList = this.getSourceFilesApi().listDirectories(projectId, null, null, null, null, null);
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
        ResponseObject<Directory> directoryResponseObject = this.getSourceFilesApi().getDirectory(projectId, directoryId);
        assertEquals(directoryResponseObject.getData().getId(), directoryId);
        assertEquals(directoryResponseObject.getData().getName(), directoryName);
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
        ResponseList<File> fileResponseList = this.getSourceFilesApi().listFiles(projectId, null, null, null, null, null);
        assertEquals(fileResponseList.getData().size(), 3);
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
    }

    @Test
    public void addFileTest() {
        AddFileRequest request = new AddFileRequest();
        request.setName(fileName);
        request.setStorageId(storageId);
        ResponseObject<File> fileResponseObject = this.getSourceFilesApi().addFile(projectId, request);
        assertEquals(fileResponseObject.getData().getId(), fileId);
        assertEquals(fileResponseObject.getData().getName(), fileName);
    }

    @Test
    public void getFileTest() {
        ResponseObject<File> fileResponseObject = this.getSourceFilesApi().getFile(projectId, fileId);
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
        ResponseObject<File> fileResponseObject = this.getSourceFilesApi().updateOrRestoreFile(projectId, fileId, request);
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
        ResponseObject<File> fileResponseObject = this.getSourceFilesApi().editFile(projectId, fileId, Arrays.asList(request));
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
}
