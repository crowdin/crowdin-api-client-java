package com.crowdin.common.models;

import java.util.List;

public class Language {
    private Long id;
    private Long organizationId;
    private String name;
    private int dialectOf;
    private String textDirection;
    private String internalCode;
    private String editorCode;
    private String crowdinCode;
    private String code;
    private List<String> pluralCategoryNames;
    private String pluralRules;
    private List<String> pluralExamples;
    private String iso6391;
    private String twoLettersCode;
    private String iso6393;
    private String threeLettersCode;
    private String locale;
    private String androidCode;
    private String osxCode;
    private String osxLocale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDialectOf() {
        return dialectOf;
    }

    public void setDialectOf(int dialectOf) {
        this.dialectOf = dialectOf;
    }

    public String getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(String textDirection) {
        this.textDirection = textDirection;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getEditorCode() {
        return editorCode;
    }

    public void setEditorCode(String editorCode) {
        this.editorCode = editorCode;
    }

    public String getCrowdinCode() {
        return crowdinCode;
    }

    public void setCrowdinCode(String crowdinCode) {
        this.crowdinCode = crowdinCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getPluralCategoryNames() {
        return pluralCategoryNames;
    }

    public void setPluralCategoryNames(List<String> pluralCategoryNames) {
        this.pluralCategoryNames = pluralCategoryNames;
    }

    public String getPluralRules() {
        return pluralRules;
    }

    public void setPluralRules(String pluralRules) {
        this.pluralRules = pluralRules;
    }

    public List<String> getPluralExamples() {
        return pluralExamples;
    }

    public void setPluralExamples(List<String> pluralExamples) {
        this.pluralExamples = pluralExamples;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getTwoLettersCode() {
        return twoLettersCode;
    }

    public void setTwoLettersCode(String twoLettersCode) {
        this.twoLettersCode = twoLettersCode;
    }

    public String getIso6393() {
        return iso6393;
    }

    public void setIso6393(String iso6393) {
        this.iso6393 = iso6393;
    }

    public String getThreeLettersCode() {
        return threeLettersCode;
    }

    public void setThreeLettersCode(String threeLettersCode) {
        this.threeLettersCode = threeLettersCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAndroidCode() {
        return androidCode;
    }

    public void setAndroidCode(String androidCode) {
        this.androidCode = androidCode;
    }

    public String getOsxCode() {
        return osxCode;
    }

    public void setOsxCode(String osxCode) {
        this.osxCode = osxCode;
    }

    public String getOsxLocale() {
        return osxLocale;
    }

    public void setOsxLocale(String osxLocale) {
        this.osxLocale = osxLocale;
    }
}

