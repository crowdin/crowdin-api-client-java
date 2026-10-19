package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Import options for String Catalog files. Extends {@link OtherFileImportOptions} only for backward compatibility of deserialized responses: the API documents just {@code importKeyAsSource} and {@code importTranslations} for this format.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StringCatalogFileImportOptions extends OtherFileImportOptions {

    private Boolean importKeyAsSource;
    private Boolean importTranslations;
}
