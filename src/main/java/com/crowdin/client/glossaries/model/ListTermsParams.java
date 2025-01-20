package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

@Data
public class ListTermsParams extends Pagination {

    private String orderBy;
    private Long userId;
    private String languageId;
    private Long conceptId;
    /**
     * @deprecated
     */
    private Long translationOfTermId;
    private String croql;
}
