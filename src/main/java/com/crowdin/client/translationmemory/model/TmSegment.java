package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.List;

@Data
public class TmSegment {
    private Long id;
    private List<TmSegmentRecord> records;
}
