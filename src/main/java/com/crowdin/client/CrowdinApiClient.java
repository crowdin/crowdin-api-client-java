package com.crowdin.client;

import com.crowdin.client.api.BranchesApi;
import com.crowdin.client.api.DirectoriesApi;
import com.crowdin.client.api.FilesApi;
import com.crowdin.common.Settings;
import com.crowdin.common.models.*;
import com.crowdin.common.request.BranchPayload;
import com.crowdin.common.request.DirectoryPayload;
import com.crowdin.common.request.FilePayload;
import com.crowdin.common.request.PatchOperation;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;

import java.util.List;

@SuppressWarnings("unused")
public class CrowdinApiClient {

    private Settings settings;

    public CrowdinApiClient(Settings settings) {
        this.settings = settings;
    }

    /*BRANCHES*/
    public Page<Branch> getBranches(String projectId, Pageable pageable) {
        return new BranchesApi(settings).getBranches(projectId, pageable).getResponseEntity();
    }

    public SimpleResponse<Branch> createBranch(String projectId, BranchPayload branch) {
        return new BranchesApi(settings).createBranch(projectId, branch).getResponseEntity();
    }

    public Page<Branch> getBranch(String projectId, String branchId) {
        return new BranchesApi(settings).getBranch(projectId, branchId).getResponseEntity();
    }

    public void deleteBranch(String projectId, String branchId) {
        new BranchesApi(settings).deleteBranch(projectId, branchId).execute();
    }

    public SimpleResponse<Branch> updateBranch(String projectId, String branchId, List<PatchOperation> updateOperations) {
        return new BranchesApi(settings).updateBranch(projectId, branchId, updateOperations).getResponseEntity();
    }

    /*DIRECTORIES*/
    public Page<Directory> getBranches(String projectId, String branchId, Pageable pageable) {
        return new DirectoriesApi(settings).getBranchDirectories(projectId, branchId, pageable).getResponseEntity();
    }

    public Page<Directory> getSubDirectories(String projectId, String directoryId, Pageable pageable) {
        return new DirectoriesApi(settings).getSubDirectories(projectId, directoryId, pageable).getResponseEntity();
    }

    public Page<Directory> getProjectDirectories(String projectId, Pageable pageable) {
        return new DirectoriesApi(settings).getProjectDirectories(projectId, pageable).getResponseEntity();
    }

    public SimpleResponse<Directory> createDirectory(String projectId, DirectoryPayload directory) {
        return new DirectoriesApi(settings).createDirectory(projectId, directory).getResponseEntity();
    }

    public SimpleResponse<Directory> getDirectory(String projectId, String directoryId) {
        return new DirectoriesApi(settings).getDirectory(projectId, directoryId).getResponseEntity();
    }

    public void deleteDirectory(String projectId, String directoryId) {
        new DirectoriesApi(settings).deleteDirectory(projectId, directoryId).execute();
    }

    public SimpleResponse<Directory> updateDirectory(String projectId, String directoryId, List<PatchOperation> updateOperations) {
        return new DirectoriesApi(settings).updateDirectory(projectId, directoryId, updateOperations).getResponseEntity();
    }

    /*FILES*/
    public Page<FileEntity> getBranchFiles(String projectId, String branchId, Pageable pageable) {
        return new FilesApi(settings).getBranchFiles(projectId, branchId, pageable).getResponseEntity();
    }

    public Page<FileEntity> getDirectoryFiles(String projectId, String directoryId, Pageable pageable) {
        return new FilesApi(settings).getDirectoryFiles(projectId, directoryId, pageable).getResponseEntity();
    }

    public Page<FileEntity> getProjectFiles(String projectId, Pageable pageable) {
        return new FilesApi(settings).getProjectFiles(projectId, pageable).getResponseEntity();
    }

    public SimpleResponse<FileEntity> createFile(String projectId, FilePayload filePayload) {
        return new FilesApi(settings).createFile(projectId, filePayload).getResponseEntity();
    }

    public SimpleResponse<FileEntity> getFile(String projectId, String fileId) {
        return new FilesApi(settings).getFile(projectId, fileId).getResponseEntity();
    }

    public void deleteFile(String projectId, String fileId) {
        new FilesApi(settings).deleteFile(projectId, fileId).execute();
    }

    public SimpleResponse<FileEntity> updateFile(String projectId, String fileId, List<PatchOperation> updateOperations) {
        return new FilesApi(settings).updateFile(projectId, fileId, updateOperations).getResponseEntity();
    }

    public SimpleResponse<FileRaw> exportFile(String projectId, String fileId) {
        return new FilesApi(settings).exportFile(projectId, fileId).getResponseEntity();
    }
}
