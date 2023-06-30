package com.crowdin.client.translationmemory.model;

import lombok.Data;

@Data
public class TmSegmentRecordOperationAdd {
    private String text;
    private String languageId;
}
