package com.crowdin.common.dto;

public class IdDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "id=" + id +
                '}';
    }
}
