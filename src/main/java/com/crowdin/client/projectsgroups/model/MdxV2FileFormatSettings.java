package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MdxV2FileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private List<String> excludedFrontMatterElements;
    private Boolean excludeCodeBlocks;
    /**
     * One of: asterisk, underscore
     */
    private String strongMarker;
    /**
     * One of: asterisk, underscore
     */
    private String emphasisMarker;
    /**
     * One of: asterisks, plus, dash
     */
    private String unorderedListBullet;
    /**
     * One of: consolidate, evenly_distribute_cells
     */
    private String tableColumnWidth;
    private Boolean customSegmentation;
}
