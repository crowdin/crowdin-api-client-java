package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateTmSegmentRequest {
    private List<TmSegmentRecordForm> records;
}
