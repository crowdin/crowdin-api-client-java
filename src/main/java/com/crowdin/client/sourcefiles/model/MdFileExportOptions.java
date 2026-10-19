package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Export options for Markdown files. {@code exportPattern} is inherited from {@link GeneralFileExportOptions}.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MdFileExportOptions extends GeneralFileExportOptions {

    private MdMarker strongMarker;
    private MdMarker emphasisMarker;
    private MdUnorderedListBullet unorderedListBullet;
    private MdTableColumnWidth tableColumnWidth;
    private MdFrontMatterQuotes frontMatterQuotes;
}
