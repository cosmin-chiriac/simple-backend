package com.simple.simplebackend.enumtype;

public enum ValidationMessagesEnum {

    EMAIL_NOT_VALID("This is not a valid email address");

    private String value;

    ValidationMessagesEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
