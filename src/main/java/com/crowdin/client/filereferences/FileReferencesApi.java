package com.crowdin.client.filereferences;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.filereferences.model.FileReference;
import com.crowdin.client.filereferences.model.AddFileReferenceRequest;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileReferencesApi extends CrowdinApi {

    public FileReferencesApi(com.crowdin.client.core.model.Credentials credentials) {
        super(credentials);
    }

    /**
     * List File References
     */
    public ResponseList<FileReference> listFileReferences(Long projectId) {
        String url = String.format("%s/projects/%d/file-references", this.url, projectId);
        return this.httpClient.get(url, new TypeReference<ResponseList<FileReference>>() {});
    }

    /**
     * Get File Reference by ID
     */
    public ResponseObject<FileReference> getFileReference(Long projectId, Long fileReferenceId) {
        String url = String.format("%s/projects/%d/file-references/%d", this.url, projectId, fileReferenceId);
        return this.httpClient.get(url, new TypeReference<ResponseObject<FileReference>>() {});
    }

    /**
     * Add File Reference
     */
    public ResponseObject<FileReference> addFileReference(Long projectId, AddFileReferenceRequest request) {
        String url = String.format("%s/projects/%d/file-references", this.url, projectId);
        return this.httpClient.post(url, request, new TypeReference<ResponseObject<FileReference>>() {});
    }

    /**
     * Delete File Reference
     */
    public void deleteFileReference(Long projectId, Long fileReferenceId) {
        String url = String.format("%s/projects/%d/file-references/%d", this.url, projectId, fileReferenceId);
        this.httpClient.delete(url);
    }
}
