package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class OtherFileImportOptions extends ImportOptions {

    private Boolean contentSegmentation;
    private Boolean customSegmentation;
}
