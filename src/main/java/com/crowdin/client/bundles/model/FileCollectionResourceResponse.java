package com.crowdin.client.bundles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileCollectionResourceResponse extends FileInfoCollectionResourceResponse{

    private Long revisionId;
    private String priority;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<String> excludedTargetLanguages;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class ImportOptions {
        Boolean firstLineContainsHeader;
        Boolean importTranslations;
        private Scheme scheme;
    }

    @Data
    public static class Scheme {
        private Long identifier;
        private Long sourcePhrase;
        private Long en;
        private Long de;
    }

    @Data
    public static class ExportOptions {
        private String exportPattern;
    }
}
