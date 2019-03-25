package com.crowdin.common.dto;

public class StorageDto {

    private long storageId;

    public StorageDto(long storageId) {
        this.storageId = storageId;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }
}

