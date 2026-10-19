package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Import options for VDF files. Extends {@link OtherFileImportOptions} only for backward compatibility of deserialized responses: the API documents just {@code convertIcu} and {@code addGenderArgument} for this format.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VdfFileImportOptions extends OtherFileImportOptions {

    private Boolean convertIcu;
    private Boolean addGenderArgument;
}
