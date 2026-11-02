package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

/**
 * Translation of an asset file (e.g. an image). Returned by List Language Translations for asset content types
 * such as {@code image/jpeg}.
 */
@Data
public class AssetLanguageTranslations implements LanguageTranslations {

    private Long stringId;
    private String contentType;
    private Long translationId;
    private String url;
    private User user;
    private Date createdAt;
    /**
     * One of {@code inProgress}, {@code failed}, {@code passed}
     */
    private String qaIssuesStatus;
}
