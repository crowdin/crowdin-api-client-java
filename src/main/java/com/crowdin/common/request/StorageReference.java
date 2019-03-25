package com.crowdin.common.request;

public class StorageReference {

    private String name;
    private String storageId;

    public StorageReference(String name, String storageId) {
        this.name = name;
        this.storageId = storageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}