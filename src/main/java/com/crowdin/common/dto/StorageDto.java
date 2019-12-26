package com.crowdin.common.dto;

public class StorageDto {

    private Long storageId;

    public StorageDto(Long storageId) {
        this.storageId = storageId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}

