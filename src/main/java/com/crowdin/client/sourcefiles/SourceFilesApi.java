package com.crowdin.client.sourcefiles;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.sourcefiles.model.AddBranchRequest;
import com.crowdin.client.sourcefiles.model.AddDirectoryRequest;
import com.crowdin.client.sourcefiles.model.AddFileRequest;
import com.crowdin.client.sourcefiles.model.Branch;
import com.crowdin.client.sourcefiles.model.BranchResponseList;
import com.crowdin.client.sourcefiles.model.BranchResponseObject;
import com.crowdin.client.sourcefiles.model.Directory;
import com.crowdin.client.sourcefiles.model.DirectoryResponseList;
import com.crowdin.client.sourcefiles.model.DirectoryResponseObject;
import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileResponseList;
import com.crowdin.client.sourcefiles.model.FileResponseObject;
import com.crowdin.client.sourcefiles.model.FileRevision;
import com.crowdin.client.sourcefiles.model.FileRevisionResponseList;
import com.crowdin.client.sourcefiles.model.FileRevisionResponseObject;
import com.crowdin.client.sourcefiles.model.UpdateOrRestoreFileRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SourceFilesApi extends CrowdinApi {
    public SourceFilesApi(Credentials credentials) {
        super(credentials);
    }

    public SourceFilesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param name      filter by branch name
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of branches
     */
    public ResponseList<Branch> listBranches(Integer projectId, String name, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "name", Optional.ofNullable(name),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        BranchResponseList branchResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/branches", new HttpConfig(queryParams), BranchResponseList.class);
        return BranchResponseList.to(branchResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created branch
     */
    public ResponseObject<Branch> addBranch(Integer projectId, AddBranchRequest request) throws HttpException, HttpBadRequestException {
        BranchResponseObject branchResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/branches", request, new HttpConfig(), BranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     * @return branch
     */
    public ResponseObject<Branch> getBranch(Integer projectId, Integer branchId) throws HttpException, HttpBadRequestException {
        BranchResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId, new HttpConfig(), BranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     */
    public void deleteBranch(Integer projectId, Integer branchId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/branches/" + branchId, new HttpConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     * @param request   request object
     * @return updated branch
     */
    public ResponseObject<Branch> editBranch(Integer projectId, Integer branchId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        BranchResponseObject groupResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/branches/" + branchId, request, new HttpConfig(), BranchResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param branchId    filter by branch id
     * @param directoryId filter by directory id
     * @param recursion   use to list directories recursively
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @return list of directories
     */
    public ResponseList<Directory> listDirectories(Integer projectId, Integer branchId, Integer directoryId, Object recursion, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "branchId", Optional.ofNullable(branchId),
                "directoryId", Optional.ofNullable(directoryId),
                "recursion", Optional.ofNullable(recursion),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        DirectoryResponseList directoryResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/directories", new HttpConfig(queryParams), DirectoryResponseList.class);
        return DirectoryResponseList.to(directoryResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created directory
     */
    public ResponseObject<Directory> addDirectory(Integer projectId, AddDirectoryRequest request) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject post = this.httpClient.post(this.url + "/projects/" + projectId + "/directories", request, new HttpConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(post.getData());
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     * @return directory
     */
    public ResponseObject<Directory> getDirectory(Integer projectId, Integer directoryId) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject directoryResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/directories/" + directoryId, new HttpConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(directoryResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     */
    public void deleteDirectory(Integer projectId, Integer directoryId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/directories/" + directoryId, new HttpConfig(), Void.class);
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     * @param request     request object
     * @return updated directory
     */
    public ResponseObject<Directory> editDirectory(Integer projectId, Integer directoryId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject directoryResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/directories/" + directoryId, request, new HttpConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(directoryResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param branchId    filter by branch id
     * @param directoryId filter by directory id
     * @param recursion   use to list directories recursively
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @return list of files
     */
    public ResponseList<File> listFiles(Integer projectId, Integer branchId, Integer directoryId, Object recursion, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "branchId", Optional.ofNullable(branchId),
                "directoryId", Optional.ofNullable(directoryId),
                "recursion", Optional.ofNullable(recursion),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileResponseList fileResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/files", new HttpConfig(queryParams), FileResponseList.class);
        return FileResponseList.to(fileResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created file
     */
    public ResponseObject<File> addFile(Integer projectId, AddFileRequest request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/files", request, new HttpConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @return file
     */
    public ResponseObject<File> getFile(Integer projectId, Integer fileId) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId, new HttpConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param request   request object
     * @return updated file
     */
    public ResponseObject<File> updateOrRestoreFile(Integer projectId, Integer fileId, UpdateOrRestoreFileRequest request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.put(this.url + "/projects/" + projectId + "/files/" + fileId, request, new HttpConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     */
    public void deleteFile(Integer projectId, Integer fileId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/files/" + fileId, new HttpConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param request   request object
     * @return updated file
     */
    public ResponseObject<File> editFile(Integer projectId, Integer fileId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/files/" + fileId, request, new HttpConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @return file download link
     */
    public ResponseObject<DownloadLink> downloadFile(Integer projectId, Integer fileId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/download", new HttpConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of file revisions
     */
    public ResponseList<FileRevision> listFileRevisions(Integer projectId, Integer fileId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileRevisionResponseList fileRevisionResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions", new HttpConfig(queryParams), FileRevisionResponseList.class);
        return FileRevisionResponseList.to(fileRevisionResponseList);
    }

    /**
     * @param projectId  project identifier
     * @param fileId     file identifier
     * @param revisionId revision identifier
     * @return file revision
     */
    public ResponseObject<FileRevision> getFileRevision(Integer projectId, Integer fileId, Integer revisionId) throws HttpException, HttpBadRequestException {
        FileRevisionResponseObject fileResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions/" + revisionId, new HttpConfig(), FileRevisionResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }
}
