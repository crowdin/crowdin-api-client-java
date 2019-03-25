package com.crowdin.client;

import com.crowdin.client.api.BranchesApi;
import com.crowdin.client.api.DirectoriesApi;
import com.crowdin.client.api.FilesApi;
import com.crowdin.client.api.GlossariesApi;
import com.crowdin.common.Settings;
import com.crowdin.common.dto.FormatDto;
import com.crowdin.common.dto.NameDto;
import com.crowdin.common.models.*;
import com.crowdin.common.request.FilePayload;
import com.crowdin.common.request.GlossaryPayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.sun.istack.internal.Nullable;

import java.util.List;

public class CrowdinApiClient {

    private Settings settings;

    public CrowdinApiClient(Settings settings) {
        this.settings = settings;
    }


    /*BRANCHES*/
    public Page<com.crowdin.common.models.Branch> getBranches(String userId, String projectId, @Nullable Pageable pageable) {
        return new BranchesApi(settings).getBranches(userId, projectId, pageable).execute();
    }

    public SimpleResponse<com.crowdin.common.models.Branch> createBranch(String userId, String projectId, com.crowdin.common.models.Branch branch) {
        return new BranchesApi(settings).createBranch(userId, projectId, branch).execute();
    }

    public Page<com.crowdin.common.models.Branch> getBranch(String userId, String projectId, String branchId) {
        return new BranchesApi(settings).getBranch(userId, projectId, branchId).execute();
    }

    public String deleteBranch(String userId, String projectId, String branchId) {
        return new BranchesApi(settings).deleteBranch(userId, projectId, branchId).execute();
    }

    public SimpleResponse<com.crowdin.common.models.Branch> updateBranch(String userId, String projectId, String branchId, List<PatchOperation> updateOperations) {
        return new BranchesApi(settings).updateBranch(userId, projectId, branchId, updateOperations).execute();
    }

    /*DIRECTORIES*/
    public Page<Directory> getBranches(String userId, String projectId, String branchId, @Nullable Pageable pageable) {
        return new DirectoriesApi(settings).getBranchDirectories(userId, projectId, branchId, pageable).execute();
    }

    public Page<Directory> getSubDirectories(String userId, String projectId, String directoryId, @Nullable Pageable pageable) {
        return new DirectoriesApi(settings).getSubDirectories(userId, projectId, directoryId, pageable).execute();
    }

    public Page<Directory> getProjectDirectories(String userId, String projectId, @Nullable Pageable pageable) {
        return new DirectoriesApi(settings).getProjectDirectories(userId, projectId, pageable).execute();
    }

    public SimpleResponse<Directory> createDirectory(String userId, String projectId, Directory directory) {
        return new DirectoriesApi(settings).createDirectory(userId, projectId, directory).execute();
    }

    public SimpleResponse<Directory> getDirectory(String userId, String projectId, String directoryId) {
        return new DirectoriesApi(settings).getDirectory(userId, projectId, directoryId).execute();
    }

    public String deleteDirectory(String userId, String projectId, String directoryId) {
        return new DirectoriesApi(settings).deleteDirectory(userId, projectId, directoryId).execute();
    }

    public SimpleResponse<Directory> updateDirectory(String userId, String projectId, String directoryId, List<PatchOperation> updateOperations) {
        return new DirectoriesApi(settings).updateDirectory(userId, projectId, directoryId, updateOperations).execute();
    }

    /*FILES*/
    public Page<File> getBranchFiles(String userId, String projectId, String branchId, Pageable pageable) {
        return new FilesApi(settings).getBranchFiles(userId, projectId, branchId, pageable).execute();
    }

    public Page<File> getDirectoryFiles(String userId, String projectId, String directoryId, Pageable pageable) {
        return new FilesApi(settings).getDirectoryFiles(userId, projectId, directoryId, pageable).execute();
    }

    public Page<File> getProjectFiles(String userId, String projectId, Pageable pageable) {
        return new FilesApi(settings).getProjectFiles(userId, projectId, pageable).execute();
    }

    public SimpleResponse<File> createFile(String userId, String projectId, FilePayload filePayload) {
        return new FilesApi(settings).createFile(userId, projectId, filePayload).execute();
    }

    public SimpleResponse<File> getFile(String userId, String projectId, String fileId) {
        return new FilesApi(settings).getFile(userId, projectId, fileId).execute();
    }

    public String deleteFile(String userId, String projectId, String fileId) {
        return new FilesApi(settings).deleteFile(userId, projectId, fileId).execute();
    }

    public SimpleResponse<File> updateFile(String userId, String projectId, String fileId, List<PatchOperation> updateOperations) {
        return new FilesApi(settings).updateFile(userId, projectId, fileId, updateOperations).execute();
    }

    public SimpleResponse<FileRaw> exportFile(String userId, String projectId, String fileId) {
        return new FilesApi(settings).exportFile(userId, projectId, fileId).execute();
    }

    /*GLOSSARIES*/
    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(String userId, Pageable pageable) {
        return new GlossariesApi(settings).getAllGlossaries(userId, pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> getGlossary(String userId, String glossaryId) {
        return new GlossariesApi(settings).getGlossary(userId, glossaryId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> deleteGlossary(String userId, String glossaryId) {
        return new GlossariesApi(settings).deleteGlossary(userId, glossaryId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> updateGlossary(String userId, String glossaryId, List<PatchOperation> updateOperation) {
        return new GlossariesApi(settings).updateGlossary(userId, glossaryId, updateOperation);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportGlossary(String userId, String glossaryId, FormatDto format) {
        return new GlossariesApi(settings).exportGlossary(userId, glossaryId, format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importGlossary(String userId, String glossaryId, GlossaryPayload glossary) {
        return new GlossariesApi(settings).importGlossary(userId, glossaryId, glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getGlossaryRaw(String userId, String glossaryId) {
        return new GlossariesApi(settings).getGlossaryRaw(userId, glossaryId);
    }

    public CrowdinRequestBuilder<Page<Glossary>> getAllGlossaries(String userId, String groupId, Pageable pageable) {
        return new GlossariesApi(settings).getAllGlossaries(userId, groupId, pageable);
    }

    public CrowdinRequestBuilder<SimpleResponse<Glossary>> createGroupGlossary(String userId, String groupId, NameDto name) {
        return new GlossariesApi(settings).createGroupGlossary(userId, groupId, name);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> exportProjectGlossary(String userId, String projectId, FormatDto format) {
        return new GlossariesApi(settings).exportProjectGlossary(userId, projectId, format);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> importProjectGlossary(String userId, String projectId, GlossaryPayload glossary) {
        return new GlossariesApi(settings).importProjectGlossary(userId, projectId, glossary);
    }

    public CrowdinRequestBuilder<SimpleResponse<FileRaw>> getProjectGlossaryRaw(String userId, String projectId) {
        return new GlossariesApi(settings).getProjectGlossaryRaw(userId, projectId);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryExportStatus(String userId, String glossaryId, String jobIdentifier) {
        return new GlossariesApi(settings).getGlossaryExportStatus(userId, glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getGlossaryImportStatus(String userId, String glossaryId, String jobIdentifier) {
        return new GlossariesApi(settings).getGlossaryImportStatus(userId, glossaryId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryExportStatus(String userId, String projectId, String jobIdentifier) {
        return new GlossariesApi(settings).getProjectGlossaryExportStatus(userId, projectId, jobIdentifier);
    }

    public CrowdinRequestBuilder<SimpleResponse<Status>> getProjectGlossaryImportStatus(String userId, String projectId, String jobIdentifier) {
        return new GlossariesApi(settings).getProjectGlossaryImportStatus(userId, projectId, jobIdentifier);
    }
}
