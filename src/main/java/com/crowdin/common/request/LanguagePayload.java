package com.crowdin.common.request;

import java.util.List;

public class LanguagePayload {

    private String name;
    private int dialectOf;
    private String code;
    private String localeCode;
    private String twoLettersCode;
    private String threeLettersCode;
    private String textDirection;
    private List<String> pluralCategoryNames;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getTwoLettersCode() {
        return twoLettersCode;
    }

    public void setTwoLettersCode(String twoLettersCode) {
        this.twoLettersCode = twoLettersCode;
    }

    public String getThreeLettersCode() {
        return threeLettersCode;
    }

    public void setThreeLettersCode(String threeLettersCode) {
        this.threeLettersCode = threeLettersCode;
    }

    public String getTextDirection() {
        return textDirection;
    }

    //todo must be array
    public void setTextDirection(String textDirection) {
        this.textDirection = textDirection;
    }

    public List<String> getPluralCategoryNames() {
        return pluralCategoryNames;
    }

    public void setPluralCategoryNames(List<String> pluralCategoryNames) {
        this.pluralCategoryNames = pluralCategoryNames;
    }
}
