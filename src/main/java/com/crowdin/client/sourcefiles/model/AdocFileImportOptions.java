package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Import options for AsciiDoc files. Extends {@link OtherFileImportOptions} only for backward compatibility of deserialized responses: the API documents just {@code excludeIncludeDirectives} for this format.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdocFileImportOptions extends OtherFileImportOptions {

    private Boolean excludeIncludeDirectives;
}
