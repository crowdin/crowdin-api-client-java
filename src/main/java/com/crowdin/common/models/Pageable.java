package com.crowdin.common.models;

public class Pageable {

    private Integer offset;
    private Integer limit;

    public Pageable() {
    }

    private Pageable(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public static Pageable of(Integer offset, Integer limit) {
        return new Pageable(offset, limit);
    }

    public static Pageable unpaged() {
        return null;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
