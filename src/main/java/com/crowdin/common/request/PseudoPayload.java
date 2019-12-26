package com.crowdin.common.request;

public class PseudoPayload {

    private String prefix;
    private String suffix;
    private int lengthTransformation;
    private String charTransformation;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getLengthTransformation() {
        return lengthTransformation;
    }

    public void setLengthTransformation(int lengthTransformation) {
        this.lengthTransformation = lengthTransformation;
    }

    public String getCharTransformation() {
        return charTransformation;
    }

    public void setCharTransformation(String charTransformation) {
        this.charTransformation = charTransformation;
    }
}
