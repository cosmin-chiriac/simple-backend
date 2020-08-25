package com.simple.simplebackend.enumtype;

public enum ArticleTypeEnum {
    LIFESTYLE("LIFESTYLE"),
    TRAVEL("TRAVEL"),
    TECHNOLOGY("TECHNOLOGY");

    private String value;

    ArticleTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
