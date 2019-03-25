package com.crowdin.common.request;

public class PatchOperation {
    private String op;
    private String path;

    private PatchOperation() {
    }

    public PatchOperation(String op, String path) {
        this.op = op;
        this.path = path;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
