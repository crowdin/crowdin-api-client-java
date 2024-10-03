package com.crowdin.client.sourcestrings.model;

import com.crowdin.client.sourcefiles.model.UpdateOption;
import lombok.Data;

import java.util.List;

@Data
public class UploadStringsRequest {

    private Long branchId;
    private Long storageId;
    private String type;
    private Integer parserVersion;
    private List<Long> labelIds;
    private Boolean updateStrings;
    private Boolean cleanupMode;
    private ImportOptions importOptions;
    private UpdateOption updateOption;

    public void setUpdateStrings(Boolean updateStrings) {
        this.updateStrings = updateStrings;
        if (updateStrings) {
            this.updateOption = UpdateOption.CLEAR_TRANSLATIONS_AND_APPROVALS;
        }
    }

    public void setUpdateOption(UpdateOption updateOption) {
        this.updateOption = updateOption;
        if (!Boolean.TRUE.equals(this.updateStrings)) {
            this.updateStrings = true;
        }
    }
}
