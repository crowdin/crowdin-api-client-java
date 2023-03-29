package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class OtherFileImportOptions extends ImportOptions {

    private Boolean contentSegmentation;
    private Boolean customSegmentation;
    private Long srxStorageId;
}
